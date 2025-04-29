package com.example.cryptopulse.ui.presentation.onboarding.widget

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cryptopulse.model.data.OnboardingData
import kotlinx.coroutines.delay

@Composable
fun OnboardingItem(page: OnboardingData) {
    var isVisible by remember { mutableStateOf(false) }
    val imageScale by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0.8f,
        animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing),
        label = "Image Scale"
    )
    val titleAlpha by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0f,
        animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing),
        label = "Title Alpha"
    )
    val descriptionAlpha by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0f,
        animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing),
        label = "Description Alpha"
    )
    LaunchedEffect(key1 = true) {
        delay(200)
        isVisible = true
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AnimatedVisibility(
            visible = isVisible,
            enter = fadeIn(animationSpec = tween(durationMillis = 500)),
            exit = fadeOut(animationSpec = tween(durationMillis = 500))
        ) {
            Image(
                painter = painterResource(page.image),
                contentDescription = null,
                modifier = Modifier
                    .size(250.dp)
                    .scale(imageScale)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = page.title,
            modifier = Modifier
                .fillMaxWidth()
                .alpha(titleAlpha),
            style = MaterialTheme.typography.headlineLarge.copy(
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = page.description,
            modifier = Modifier
                .fillMaxWidth()
                .alpha(descriptionAlpha),
            style = MaterialTheme.typography.labelSmall.copy(
                textAlign = TextAlign.Center,
                color = Color.Gray,
                lineHeight = 20.sp,
                fontSize = 14.sp
            )
        )
    }
}