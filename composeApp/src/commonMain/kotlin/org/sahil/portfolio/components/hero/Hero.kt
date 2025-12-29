package org.sahil.portfolio.components.hero

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowForwardIos
import androidx.compose.material.icons.outlined.Download
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.sahil.portfolio.util.WindowType
import sahil_portfolio.composeapp.generated.resources.Res
import sahil_portfolio.composeapp.generated.resources.github
import sahil_portfolio.composeapp.generated.resources.instagram
import sahil_portfolio.composeapp.generated.resources.linkedin
import sahil_portfolio.composeapp.generated.resources.sahil

@Composable
fun HeroSection() {
    val infiniteTransition = rememberInfiniteTransition(label = "floating")

    val offsetY by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 8f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 4000,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = "floatingOffset"
    )
    val density = LocalDensity.current
    val offsetYPx = with(density) { offsetY.dp.toPx() }

    BoxWithConstraints {
        val windowType = WindowType.fromWidth(maxWidth)
        val maxWidthDp = maxWidth
        val topPadding = when (windowType) {
            WindowType.Compact -> 40.dp
            else -> 120.dp
        }
        Column(
            modifier = Modifier.padding(24.dp).padding(top = topPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (windowType) {
                WindowType.Compact -> HeroCompact(
                    offsetYPx = offsetYPx,
                    maxWidthDp
                )

                WindowType.Expanded -> HeroExpanded(
                    offsetYPx = offsetYPx
                )
            }
            Spacer(modifier = Modifier.height(80.dp))
        }

    }
}

@Composable
fun HeroCompact(offsetYPx: Float, imageWidth: Dp) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeroInfo(
            modifier = Modifier.padding(end = 30.dp),
            style = MaterialTheme.typography.displaySmall
        )
        Spacer(modifier = Modifier.height(40.dp))
        HeroImage(offsetYPx = offsetYPx, modifier = Modifier.width(imageWidth))
    }
}

@Composable
fun HeroExpanded(offsetYPx: Float) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        HeroInfo(modifier = Modifier.weight(1f))
        Spacer(modifier = Modifier.width(36.dp))
        HeroImage(offsetYPx = offsetYPx, modifier = Modifier.weight(1f))
    }
}

@Composable
fun HeroInfo(
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.displayLarge
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start
    ) {
        FlowRow {
            Text(
                "Hi, I'm ",
                style = style,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                "Sahil Karwasra",
                style = style,
                color = MaterialTheme.colorScheme.primary
            )
        }
        Text(
            "Full Stack Mobile App Developer",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground.copy(0.5f)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            "Crafting beautiful, functional mobile experiences with Kotlin, Flutter, and more. Let's build something amazing together.",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground.copy(0.5f)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Row {
            Button(onClick = {}, shape = RoundedCornerShape(12.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Contact Me")
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        Icons.AutoMirrored.Outlined.ArrowForwardIos,
                        contentDescription = "Contact Me",
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.width(12.dp))
            Button(
                onClick = {},
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.primary.copy(0.5f)
                ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.onBackground
                )
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Resume")
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        Icons.Outlined.Download,
                        contentDescription = "Contact Me",
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Row {
            HeroSocialIcons(Res.drawable.github)
            Spacer(modifier = Modifier.width(12.dp))
            HeroSocialIcons(Res.drawable.linkedin)
            Spacer(modifier = Modifier.width(12.dp))
            HeroSocialIcons(Res.drawable.instagram)
        }
    }
}

@Composable
fun HeroImage(offsetYPx: Float, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(Res.drawable.sahil),
            contentDescription = "About Me",
            contentScale = ContentScale.Crop,
            modifier = modifier.height(440.dp).width(520.dp)
                .graphicsLayer(translationY = offsetYPx).clip(RoundedCornerShape(28.dp))
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.08f),
                    shape = RoundedCornerShape(28.dp)
                )
        )
    }
}