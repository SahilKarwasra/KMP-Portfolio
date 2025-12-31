package org.sahil.portfolio.components.footer

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import org.sahil.portfolio.components.hero.HeroSocialIcons
import org.sahil.portfolio.util.WindowType
import sahil_portfolio.composeapp.generated.resources.Res
import sahil_portfolio.composeapp.generated.resources.github
import sahil_portfolio.composeapp.generated.resources.instagram
import sahil_portfolio.composeapp.generated.resources.linkedin


data class SocialLink(
    val platform: String,
    val url: String,
    val icon: ImageVector
)

@Composable
fun FooterSection(modifier: Modifier = Modifier) {
    val uriHandler = LocalUriHandler.current

    val socialLinks = remember {
        listOf(
            SocialLink(
                platform = "GitHub",
                url = "https://github.com/sahilkarwasra",
                icon = Icons.Default.Code
            ),
            SocialLink(
                platform = "LinkedIn",
                url = "https://linkedin.com/in/sahilkarwasra",
                icon = Icons.Default.Person
            ),
            SocialLink(
                platform = "Instagram",
                url = "https://instagram.com/karwasra_sahil11",
                icon = Icons.Default.Photo
            )
        )
    }

    BoxWithConstraints(
        modifier = modifier.fillMaxWidth()
    ) {
        val windowType = WindowType.fromWidth(maxWidth)

        Box(modifier = Modifier.fillMaxWidth()) {
            FooterContent(
                windowType = windowType,
                onSocialClick = { url -> uriHandler.openUri(url) }
            )
        }
    }
}

@Composable
private fun FooterContent(
    windowType: WindowType,
    onSocialClick: (String) -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 48.dp)
    ) {
        when (windowType) {
            WindowType.Compact -> {
                CompactFooterLayout(
                    onSocialClick = onSocialClick
                )
            }
            WindowType.Expanded -> {
                ExpandedFooterLayout(
                    onSocialClick = onSocialClick
                )
            }
        }
    }
}

@Composable
private fun CompactFooterLayout(
    onSocialClick: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        BrandSection(textAlign = TextAlign.Center)
        SocialLinksRow( onSocialClick = onSocialClick)
        AnimatedCopyright(textAlign = TextAlign.Center)
    }
}

@Composable
private fun ExpandedFooterLayout(
    onSocialClick: (String) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BrandSection(textAlign = TextAlign.Start)

        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            SocialLinksRow( onSocialClick = onSocialClick)
            AnimatedCopyright(textAlign = TextAlign.End)
        }
    }
}

@Composable
private fun BrandSection(textAlign: TextAlign) {
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(100)
        visible = true
    }

    val offsetY by animateDpAsState(
        targetValue = if (visible) 0.dp else 20.dp,
        animationSpec = tween(500)
    )

    val alpha by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(500)
    )

    Column(
        modifier = Modifier.offset(y = offsetY),
        horizontalAlignment = when (textAlign) {
            TextAlign.Center -> Alignment.CenterHorizontally
            TextAlign.End -> Alignment.End
            else -> Alignment.Start
        }
    ) {
        // Brand Name with animated colors
        val brandText = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = MaterialTheme.colorScheme.primary.copy(alpha = alpha),
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
            ) {
                append("Sahil")
            }
            append(" ")
            withStyle(
                style = SpanStyle(
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = alpha),
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
            ) {
                append("Karwasra")
            }
        }

        Text(
            text = brandText,
            modifier = Modifier.clickable(
                onClick = { /* Navigate to home */ },
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Mobile App Developer crafting beautiful digital experiences",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = alpha),
            textAlign = textAlign,
            modifier = Modifier.widthIn(max = 300.dp)
        )
    }
}

@Composable
private fun SocialLinksRow(
    onSocialClick: (String) -> Unit
) {
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(200)
        visible = true
    }

    val offsetY by animateDpAsState(
        targetValue = if (visible) 0.dp else 20.dp,
        animationSpec = tween(500, delayMillis = 200)
    )


    Row(
        modifier = Modifier.offset(y = offsetY),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
            HeroSocialIcons(Res.drawable.github, onClick = {onSocialClick("https://github.com/sahilkarwasra")})
            HeroSocialIcons(Res.drawable.linkedin, onClick = {onSocialClick("https://linkedin.com/in/sahilkarwasra")})
            HeroSocialIcons(Res.drawable.instagram, onClick = {onSocialClick("https://instagram.com/karwasra_sahil11")})
    }
}

@Composable
private fun SocialIconButton(
    icon: ImageVector,
    platform: String,
    onClick: () -> Unit,
    alpha: Float
) {
    var isPressed by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.9f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        )
    )

    val rotation by animateFloatAsState(
        targetValue = if (isPressed) 5f else 0f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        )
    )

    val backgroundColor by animateColorAsState(
        targetValue = if (isPressed)
            MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
        else
            MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
        animationSpec = tween(200)
    )

    Box(
        modifier = Modifier
            .size(40.dp)
            .scale(scale)
            .rotate(rotation)
            .clip(CircleShape)
            .background(backgroundColor.copy(alpha = backgroundColor.alpha * alpha))
            .clickable(
                onClick = {
                    isPressed = true
                    onClick()
                },
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = platform,
            modifier = Modifier.size(20.dp),
            tint = MaterialTheme.colorScheme.primary.copy(alpha = alpha)
        )
    }

    LaunchedEffect(isPressed) {
        if (isPressed) {
            delay(150)
            isPressed = false
        }
    }
}

@Composable
private fun AnimatedCopyright(textAlign: TextAlign) {
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(400)
        visible = true
    }

    val offsetY by animateDpAsState(
        targetValue = if (visible) 0.dp else 20.dp,
        animationSpec = tween(500, delayMillis = 200)
    )

    // Animated color transition
    val infiniteTransition = rememberInfiniteTransition()

    val colorPhase by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(5000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val animatedColor = lerp(
        MaterialTheme.colorScheme.onSurfaceVariant,
        MaterialTheme.colorScheme.primary.copy(alpha = 0.8f),
        colorPhase
    )

    val alpha by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(500, delayMillis = 200)
    )

    Row(
        modifier = Modifier.offset(y = offsetY),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Made with ",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = alpha),
            textAlign = textAlign
        )

        // Animated heart icon
        AnimatedHeartIcon(alpha = alpha, colorPhase = colorPhase)

        Text(
            text = " by Sahil Karwasra",
            style = MaterialTheme.typography.bodySmall,
            color = animatedColor.copy(alpha = alpha),
            textAlign = textAlign
        )
    }
}

@Composable
private fun AnimatedHeartIcon(alpha: Float, colorPhase: Float) {
    val infiniteTransition = rememberInfiniteTransition()

    val heartScale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val heartColor = lerp(
        Color(0xFFE91E63), // Pink
        Color(0xFFFF5252), // Red
        colorPhase
    )

    Icon(
        imageVector = Icons.Default.Favorite,
        contentDescription = "Made with love",
        modifier = Modifier
            .size(14.dp)
            .scale(heartScale)
            .padding(horizontal = 2.dp),
        tint = heartColor.copy(alpha = alpha)
    )
}

@Composable
private fun AnimatedBlob(
    modifier: Modifier = Modifier,
    color: Color,
    delay: Long = 0
) {
    val infiniteTransition = rememberInfiniteTransition()

    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.4f,
        animationSpec = infiniteRepeatable(
            animation = tween(4000, delayMillis = delay.toInt()),
            repeatMode = RepeatMode.Reverse
        )
    )

    val offsetX by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 20f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, delayMillis = delay.toInt()),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(
        modifier = modifier
            .offset(x = offsetX.dp)
            .scale(scale)
            .clip(CircleShape)
            .background(color)
            .blur(80.dp)
    )
}

// Helper function to lerp between colors
private fun lerp(start: Color, end: Color, fraction: Float): Color {
    return Color(
        red = start.red + (end.red - start.red) * fraction,
        green = start.green + (end.green - start.green) * fraction,
        blue = start.blue + (end.blue - start.blue) * fraction,
        alpha = start.alpha + (end.alpha - start.alpha) * fraction
    )
}