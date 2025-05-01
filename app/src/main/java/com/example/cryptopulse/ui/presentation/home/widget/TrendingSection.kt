package com.example.cryptopulse.ui.presentation.home.widget


import android.icu.text.DecimalFormat
import android.icu.text.DecimalFormatSymbols
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.cryptopulse.model.data.TrendingData
import com.example.cryptopulse.ui.theme.onErrorDark
import com.example.cryptopulse.ui.theme.primaryLight
import com.example.cryptopulse.ui.theme.secondaryContainerDarkMediumContrast
import com.example.cryptopulse.ui.theme.tertiaryLight
import kotlinx.coroutines.delay
import java.util.Locale

@Composable
fun TrendingSection(
    title: String,
    navController: NavController,
    coins: List<TrendingData.Coin>,
) {

    HeaderSection(title , navController)

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        itemsIndexed(coins) { index, coin ->
            val delayFactor = 100L * (index + 1)
            CoinSection(coin ,delayFactor)
        }
    }



}

@Composable
fun CoinSection(data: TrendingData.Coin, delayFactor: Long) {
    var visibility by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit) {
        delay(delayFactor)
        visibility = true
    }

    AnimatedVisibility(
        visible = visibility,
        enter = fadeIn(tween(500)) + expandVertically (
            expandFrom = Alignment.Top,
            animationSpec = tween(500)
        )
    ) {

        Card(
            modifier = Modifier
                .size(150.dp)
                .shadow(8.dp , RoundedCornerShape(20.dp))
                .clickable {  },
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {

            EnhancedCryptoCardBackground(data)



        }

    }



}

@Composable
fun CoinCardContent(data: TrendingData.Coin) {
    val percentageData = percentageFormatter(data.item.data.priceChangePercentage24h.usd)
    val priceData = priceFormatter(data.item.data.price)
    val coinName = data.item.name


    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    )
    {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Row {
                Text(
                    text = percentageData,
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                )

                Icon(
                    imageVector = if (data.item.data.priceChangePercentage24h.usd >= 0) Icons
                        .Rounded.KeyboardArrowUp else
                        Icons.Rounded.KeyboardArrowDown,
                    contentDescription = null,
                    tint = if (data.item.data.priceChangePercentage24h.usd >= 0) secondaryContainerDarkMediumContrast else
                        onErrorDark,
                )
            }



            AsyncImage(
                modifier = Modifier
                    .size(25.dp)
                    .clip(CircleShape),
                model = data.item.small,
                contentDescription = null
            )

        }

        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = coinName,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = priceData
            )
        }
    }

}


@Composable
private fun EnhancedCryptoCardBackground(data: TrendingData.Coin) {


    Box(modifier = Modifier.fillMaxSize()) {
        val colors = listOf(
            primaryLight,
            primaryLight.copy(alpha = 0.8f),
            tertiaryLight
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = colors,
                        start = Offset(0f, 0f),
                        end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
                    )
                )
        )

        // Animated circles for visual interest
        val circle1Scale = remember { Animatable(0.5f) }
        val circle2Scale = remember { Animatable(0.7f) }

        LaunchedEffect(Unit) {
            circle1Scale.animateTo(
                targetValue = 0.8f,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessMedium
                )
            )

            circle2Scale.animateTo(
                targetValue = 1.1f,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessMedium
                )
            )
        }

        Canvas(modifier = Modifier.fillMaxSize()) {
            // Background decorative circles
            drawCircle(
                color = Color.White.copy(alpha = 0.05f),
                radius = size.minDimension * circle1Scale.value,
                center = Offset(x = 0f, y = size.height)
            )

            drawCircle(
                color = Color.White.copy(alpha = 0.05f),
                radius = size.minDimension * circle2Scale.value * 0.5f,
                center = Offset(x = size.width, y = 0f)
            )

            // Grid pattern for depth
            val gridSpacing = 20f
            val gridAlpha = 0.05f

            for (i in 0..size.width.toInt() step gridSpacing.toInt()) {
                drawLine(
                    color = Color.White.copy(alpha = gridAlpha),
                    start = Offset(i.toFloat(), 0f),
                    end = Offset(i.toFloat(), size.height),
                    strokeWidth = 0.5f
                )
            }

            for (i in 0..size.height.toInt() step gridSpacing.toInt()) {
                drawLine(
                    color = Color.White.copy(alpha = gridAlpha),
                    start = Offset(0f, i.toFloat()),
                    end = Offset(size.width, i.toFloat()),
                    strokeWidth = 0.5f
                )
            }
        }
        CoinCardContent(data)
    }


}

private fun percentageFormatter(number: Double) :String {
    val formatPattern = DecimalFormat("0.0")
    val percentage = formatPattern.format(number)
    return "$percentage%"
}

private fun priceFormatter(number: Double) :String {
    val symbol = DecimalFormatSymbols(Locale.UK).apply {
        groupingSeparator = ','
        decimalSeparator = '.'
    }
    val pricePattern = DecimalFormat("#,###0.00" , symbol)
    val result = pricePattern.format(number)
    return "$$result"
}


