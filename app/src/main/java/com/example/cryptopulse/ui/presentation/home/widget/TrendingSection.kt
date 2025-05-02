package com.example.cryptopulse.ui.presentation.home.widget

import android.icu.text.DecimalFormat
import android.icu.text.DecimalFormatSymbols
import android.provider.CalendarContract.Colors
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
import androidx.compose.runtime.derivedStateOf
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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.cryptopulse.model.data.TrendingData
import com.example.cryptopulse.ui.theme.onErrorDark
import com.example.cryptopulse.ui.theme.onErrorLight
import com.example.cryptopulse.ui.theme.primaryLight
import com.example.cryptopulse.ui.theme.secondaryContainerDarkMediumContrast
import com.example.cryptopulse.ui.theme.tertiaryLight
import kotlinx.coroutines.delay
import java.util.Locale

private val CARD_SIZE = 150.dp
private val CARD_CORNER_RADIUS = 20.dp
private const val ANIMATION_DURATION = 500
private const val GRID_ALPHA = 0.05f
private const val GRID_SPACING = 20f

@Composable
fun TrendingSection(
    title: String,
    navController: NavController,
    coins: List<TrendingData.Coin>,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        HeaderSection(title, navController , modifier.padding(horizontal = 16.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            itemsIndexed(coins) { index, coin ->
                val delayFactor = 100L * (index + 1)
                CoinCard(coin, delayFactor)
            }
        }
    }
}

@Composable
fun CoinCard(data: TrendingData.Coin, delayFactor: Long) {
    var visibility by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(delayFactor)
        visibility = true
    }

    AnimatedVisibility(
        visible = visibility,
        enter = fadeIn(tween(ANIMATION_DURATION)) +
                expandVertically(
                    expandFrom = Alignment.Top,
                    animationSpec = tween(ANIMATION_DURATION)
                )
    ) {
        Card(
            modifier = Modifier
                .size(CARD_SIZE)
                .shadow(8.dp, RoundedCornerShape(CARD_CORNER_RADIUS))
                .clickable { /* Implement click action */ },
            shape = RoundedCornerShape(CARD_CORNER_RADIUS),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            EnhancedCryptoCardBackground(data)
        }
    }
}

@Composable
fun CoinCardContent(data: TrendingData.Coin) {
    val percentageData = remember { percentageFormatter(data.item.data.priceChangePercentage24h.usd) }
    val priceData = remember { priceFormatter(data.item.data.price) }
    val isPositiveChange = remember { data.item.data.priceChangePercentage24h.usd >= 0 }
    val iconVector = if (isPositiveChange) Icons.Rounded.KeyboardArrowUp else Icons.Rounded.KeyboardArrowDown
    val iconTint = if (isPositiveChange) secondaryContainerDarkMediumContrast else onErrorDark

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = percentageData,
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = if (data.item.data.priceChangePercentage24h.usd >= 0) Color.White
                            else onErrorDark
                    )
                )

                Icon(
                    imageVector = iconVector,
                    contentDescription = if (isPositiveChange) "Price Up" else "Price Down",
                    tint = iconTint,
                )
            }

            AsyncImage(
                modifier = Modifier
                    .size(25.dp)
                    .clip(CircleShape),
                model = data.item.small,
                contentDescription = "${data.item.name} icon"
            )
        }

        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = data.item.name,
                style = MaterialTheme.typography.titleMedium.copy(
                    color = Color.White
                )
            )
            Text(
                text = priceData,
                style = MaterialTheme.typography.titleMedium.copy(
                    color = Color.White
                )
            )
        }
    }
}

@Composable
private fun EnhancedCryptoCardBackground(data: TrendingData.Coin) {
    val density = LocalDensity.current

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
        val circle1Scale = remember { Animatable(0.7f) }
        val circle2Scale = remember { Animatable(1f) }

        Canvas(modifier = Modifier.fillMaxSize()) {
            // Background decorative circles
            drawCircle(
                color = Color.White.copy(alpha = GRID_ALPHA),
                radius = size.minDimension * circle1Scale.value,
                center = Offset(x = 0f, y = size.height)
            )

            drawCircle(
                color = Color.White.copy(alpha = GRID_ALPHA),
                radius = size.minDimension * circle2Scale.value * 0.5f,
                center = Offset(x = size.width, y = 0f)
            )

            // Grid pattern for depth
            val gridLines = with(density) {
                (size.width / GRID_SPACING).toInt()
            }

            repeat(gridLines) { i ->
                val x = i * GRID_SPACING
                drawLine(
                    color = Color.White.copy(alpha = GRID_ALPHA),
                    start = Offset(x, 0f),
                    end = Offset(x, size.height),
                    strokeWidth = 0.5f
                )
            }

            repeat((size.height / GRID_SPACING).toInt()) { i ->
                val y = i * GRID_SPACING
                drawLine(
                    color = Color.White.copy(alpha = GRID_ALPHA),
                    start = Offset(0f, y),
                    end = Offset(size.width, y),
                    strokeWidth = 0.5f
                )
            }
        }

        CoinCardContent(data)
    }
}

// Use a singleton for formatters to avoid creating new instances on each recomposition
private object Formatters {
    private val percentageFormatter = DecimalFormat("0.0")

    private val priceSymbols = DecimalFormatSymbols(Locale.UK).apply {
        groupingSeparator = ','
        decimalSeparator = '.'
    }

    private val priceFormatter = DecimalFormat("#,###0.00", priceSymbols)

    fun formatPercentage(number: Double): String = "${percentageFormatter.format(number)}%"

    fun formatPrice(number: Double): String = "$${priceFormatter.format(number)}"
}

private fun percentageFormatter(number: Double): String = Formatters.formatPercentage(number)

private fun priceFormatter(number: Double): String = Formatters.formatPrice(number)