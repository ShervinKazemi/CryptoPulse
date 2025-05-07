package com.example.cryptopulse.ui.presentation.home.widget

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.cryptopulse.model.data.CoinsData
import com.example.cryptopulse.util.percentageFormatter
import com.example.cryptopulse.util.priceFormatter
import kotlinx.coroutines.delay

@Composable
fun TopCoins(
    title: String,
    coins: CoinsData,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth().padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        HeaderSection(
            title = title,
            navController = navController,
            modifier = Modifier.padding(vertical = 12.dp)
        )

        if (coins.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxWidth().padding(32.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No coins available",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        } else {
            LazyColumn(
                contentPadding = PaddingValues(vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(
                    items = coins,
                    key = { it.id }
                ) { coin ->
                    CoinCard(
                        coin = coin,
                        onClick = {
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun CoinCard(
    coin: CoinsData.CoinsDataItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var visible by remember { mutableStateOf(false) }
    val percentageColor = if (coin.priceChangePercentage24h >= 0)
        MaterialTheme.colorScheme.primary
    else
        MaterialTheme.colorScheme.error

    // Use tween animation instead of spring
    val enterDuration = 400
    val enterDelay = 70

    LaunchedEffect(coin.id) {
        delay((enterDelay * (coin.marketCapRank?.toInt() ?: 1)).toLong()) // Staggered animation
        visible = true
    }

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(tween(durationMillis = enterDuration)) +
                expandHorizontally(
                    expandFrom = Alignment.Start,
                    animationSpec = tween(durationMillis = enterDuration + 100)
                )
    ) {
        ElevatedCard(
            modifier = modifier.fillMaxWidth(),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.dp),
            shape = RoundedCornerShape(12.dp),
            onClick = onClick
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Coin image with rank badge
                    Box {
                        Surface(
                            modifier = Modifier
                                .size(56.dp)
                                .clip(CircleShape),
                            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f),
                            shape = CircleShape
                        ) {
                            AsyncImage(
                                modifier = Modifier
                                    .size(40.dp)
                                    .padding(8.dp),
                                model = coin.image,
                                contentDescription = "${coin.name} logo",
                            )
                        }

                        // Rank badge
                        coin.marketCapRank?.let { rank ->
                            Surface(
                                modifier = Modifier
                                    .size(22.dp)
                                    .align(Alignment.TopEnd),
                                shape = CircleShape,
                                color = MaterialTheme.colorScheme.tertiary
                            ) {
                                Text(
                                    text = rank.toInt().toString(),
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 10.sp
                                    ),
                                    color = MaterialTheme.colorScheme.onTertiary,
                                    modifier = Modifier.wrapContentSize(Alignment.Center)
                                )
                            }
                        }
                    }

                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = coin.name,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = coin.symbol?.uppercase() ?: "",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )

                            Spacer(modifier = Modifier.width(4.dp))

                            // Price change indicator dot
                            Surface(
                                modifier = Modifier.size(8.dp),
                                shape = CircleShape,
                                color = percentageColor
                            ) {}
                        }
                    }
                }

                Column(
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = priceFormatter(coin.currentPrice),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )

                    Surface(
                        shape = RoundedCornerShape(16.dp),
                        color = if (coin.priceChangePercentage24h >= 0)
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
                        else
                            MaterialTheme.colorScheme.error.copy(alpha = 0.15f),
                        modifier = Modifier.padding(top = 2.dp)
                    ) {
                        Text(
                            text = percentageFormatter(coin.priceChangePercentage24h),
                            style = MaterialTheme.typography.labelMedium,
                            color = percentageColor,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }
            }
        }
    }
}