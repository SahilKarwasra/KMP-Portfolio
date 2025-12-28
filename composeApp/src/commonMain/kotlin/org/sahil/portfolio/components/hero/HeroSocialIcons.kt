package org.sahil.portfolio.components.hero

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.imageResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun HeroSocialIcons(icon: DrawableResource) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    val scale by animateFloatAsState(
        targetValue = if (isHovered) 1.1f else 1f,
        animationSpec = tween(200),
        label = "hoverScale"
    )

    val boxBg by animateColorAsState(
        targetValue = if (isHovered) MaterialTheme.colorScheme.primary.copy(0.4f) else MaterialTheme.colorScheme.primary.copy(0.3f),
        animationSpec = tween(200),
        label = "hoverBg"
    )

    Box(modifier = Modifier.graphicsLayer(scaleX = scale, scaleY = scale).hoverable(interactionSource = interactionSource).clip(CircleShape).background(boxBg), contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(icon),
            null,
            modifier = Modifier
                .padding(6.dp)
                .size(18.dp),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
        )
    }
}