package org.sahil.portfolio.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

data class Particle(
    val id: Int,
    val x: Float,
    val y: Float,
    val size: Float,
    val delay: Float
)

data class FloatingIcon(
    val x: Float,
    val y: Float,
    val delay: Float,
    val duration: Float,
    val icon: IconType
)

enum class IconType {
    PHONE, CPU, INFINITY
}

@Composable
fun AnimatedBackground(
    modifier: Modifier = Modifier,
    isDark: Boolean
) {
    val primaryColor = MaterialTheme.colorScheme.primary
    val surfaceColor = MaterialTheme.colorScheme.surface

    var screenWidth by remember { mutableStateOf(0f) }
    var screenHeight by remember { mutableStateOf(0f) }

    // Generate particles once
    val particles = remember {
        List(50) { i ->
            Particle(
                id = i,
                x = Random.nextFloat(),
                y = Random.nextFloat(),
                size = Random.nextFloat() * 6f + 2f,
                delay = Random.nextFloat() * 10f
            )
        }
    }

    // Generate floating icons
    val floatingIcons = remember {
        listOf(
            FloatingIcon(0.15f, 0.20f, 0f, 15f, IconType.PHONE),
            FloatingIcon(0.75f, 0.40f, 2f, 18f, IconType.CPU),
            FloatingIcon(0.30f, 0.80f, 5f, 20f, IconType.INFINITY)
        )
    }

    val infiniteTransition = rememberInfiniteTransition(label = "background")

    // Animated time for smooth blob movement
    val animatedTime by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(30000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "time"
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(surfaceColor)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            // Update screen dimensions from Canvas size
            screenWidth = size.width
            screenHeight = size.height

            // Subtle animated gradient background - multiple overlapping gradients for smoothness
            drawSubtleGradients(
                primaryColor = primaryColor,
                time = animatedTime,
                isDark = isDark
            )

            // Floating blobs
            drawFloatingBlob(
                center = Offset(size.width * 0.25f, size.height * 0.25f),
                radius = 128.dp.toPx(),
                color = primaryColor,
                time = animatedTime / 60f,
                isDark = isDark
            )

            drawFloatingBlob(
                center = Offset(size.width * 0.75f, size.height * 0.75f),
                radius = 160.dp.toPx(),
                color = primaryColor,
                time = animatedTime / 80f,
                isDark = isDark
            )

            drawFloatingBlob(
                center = Offset(size.width * 0.5f, size.height * 0.75f),
                radius = 144.dp.toPx(),
                color = primaryColor,
                time = animatedTime / 100f,
                isDark = isDark
            )

            // Grid pattern
            drawGridPattern(
                primaryColor = primaryColor,
                isDark = isDark
            )
        }

        // Animated particles
        particles.forEach { particle ->
            AnimatedParticle(
                particle = particle,
                screenWidth = screenWidth,
                screenHeight = screenHeight,
                primaryColor = primaryColor,
                isDark = isDark
            )
        }

        // Floating tech icons
        floatingIcons.forEach { icon ->
            AnimatedFloatingIcon(
                icon = icon,
                screenWidth = screenWidth,
                screenHeight = screenHeight,
                primaryColor = primaryColor,
                isDark = isDark
            )
        }
    }
}

@Composable
private fun AnimatedParticle(
    particle: Particle,
    screenWidth: Float,
    screenHeight: Float,
    primaryColor: Color,
    isDark: Boolean
) {
    val infiniteTransition = rememberInfiniteTransition(label = "particle_${particle.id}")

    val yOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = -100f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = (15000 + particle.delay * 1000).toInt(),
                easing = EaseInOut
            ),
            repeatMode = RepeatMode.Restart,
            initialStartOffset = StartOffset((particle.delay * 1000).toInt())
        ),
        label = "y"
    )

    val alpha by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = (15000 + particle.delay * 1000).toInt()
                0f at 0 using EaseInOut
                1f at (durationMillis * 0.5f).toInt() using EaseInOut
                0f at durationMillis using EaseInOut
            },
            repeatMode = RepeatMode.Restart,
            initialStartOffset = StartOffset((particle.delay * 1000).toInt())
        ),
        label = "alpha"
    )

    val color = if (isDark) {
        primaryColor.copy(alpha = 0.2f * alpha)
    } else {
        primaryColor.copy(alpha = 0.15f * alpha)
    }

    Canvas(modifier = Modifier.fillMaxSize()) {
        drawCircle(
            color = color,
            radius = particle.size,
            center = Offset(
                screenWidth * particle.x,
                screenHeight * particle.y + yOffset
            )
        )
    }
}

@Composable
private fun AnimatedFloatingIcon(
    icon: FloatingIcon,
    screenWidth: Float,
    screenHeight: Float,
    primaryColor: Color,
    isDark: Boolean
) {
    val infiniteTransition = rememberInfiniteTransition(label = "icon_${icon.icon}")

    val yOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = -50f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = (icon.duration * 1000).toInt(),
                easing = EaseInOut
            ),
            repeatMode = RepeatMode.Reverse,
            initialStartOffset = StartOffset((icon.delay * 1000).toInt())
        ),
        label = "y"
    )

    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = when (icon.icon) {
            IconType.PHONE -> 10f
            IconType.CPU -> -10f
            IconType.INFINITY -> 15f
        },
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = (icon.duration * 1000).toInt(),
                easing = EaseInOut
            ),
            repeatMode = RepeatMode.Reverse,
            initialStartOffset = StartOffset((icon.delay * 1000).toInt())
        ),
        label = "rotation"
    )

    val alpha by infiniteTransition.animateFloat(
        initialValue = if (isDark) 0.2f else 0.15f,
        targetValue = if (isDark) 0.3f else 0.25f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = (icon.duration * 1000).toInt(),
                easing = EaseInOut
            ),
            repeatMode = RepeatMode.Reverse,
            initialStartOffset = StartOffset((icon.delay * 1000).toInt())
        ),
        label = "alpha"
    )

    Canvas(modifier = Modifier.fillMaxSize()) {
        drawContext.canvas.save()
        drawContext.canvas.translate(screenWidth * icon.x, screenHeight * icon.y + yOffset)
        drawContext.canvas.rotate(rotation)

        when (icon.icon) {
            IconType.PHONE -> drawPhoneIcon(primaryColor.copy(alpha = alpha))
            IconType.CPU -> drawCpuIcon(primaryColor.copy(alpha = alpha))
            IconType.INFINITY -> drawInfinityIcon(primaryColor.copy(alpha = alpha))
        }

        drawContext.canvas.restore()
    }
}

private fun DrawScope.drawSubtleGradients(
    primaryColor: Color,
    time: Float,
    isDark: Boolean
) {
    val baseAlpha = if (isDark) 0.15f else 0.1f

    // Multiple overlapping radial gradients at different positions for smooth coverage
    val gradientPositions = listOf(
        Offset(size.width * 0.3f, size.height * 0.2f),
        Offset(size.width * 0.7f, size.height * 0.4f),
        Offset(size.width * 0.5f, size.height * 0.7f),
        Offset(size.width * 0.2f, size.height * 0.6f)
    )
    val radians = time * (PI / 180f)

    gradientPositions.forEachIndexed { index, pos ->
        val timeOffset = time + (index * 90f)
        val animatedX = pos.x + cos(radians) * 100f
        val animatedY = pos.y + sin(radians) * 100f


        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(
                    primaryColor.copy(alpha = baseAlpha),
                    primaryColor.copy(alpha = baseAlpha * 0.5f),
                    primaryColor.copy(alpha = baseAlpha * 0.2f),
                    Color.Transparent
                ),
                center = Offset(animatedX.toFloat(), animatedY.toFloat()),
                radius = size.minDimension * 0.6f
            ),
            radius = size.minDimension * 0.6f,
            center = Offset(animatedX.toFloat(), animatedY.toFloat()),
            blendMode = BlendMode.Plus
        )
    }
}

private fun DrawScope.drawFloatingBlob(
    center: Offset,
    radius: Float,
    color: Color,
    time: Float,
    isDark: Boolean
) {
    val radians = time * (PI / 180f)

    val offsetX = cos(radians) * 30f
    val offsetY = sin(radians) * 30f


    val blobAlpha = if (isDark) 0.08f else 0.05f

    drawCircle(
        brush = Brush.radialGradient(
            colors = listOf(
                color.copy(alpha = blobAlpha),
                color.copy(alpha = blobAlpha * 0.5f),
                color.copy(alpha = blobAlpha * 0.2f),
                Color.Transparent
            ),
            center = center + Offset(offsetX.toFloat(), offsetY.toFloat()),
            radius = radius
        ),
        radius = radius,
        center = center + Offset(offsetX.toFloat(), offsetY.toFloat()),
        blendMode = BlendMode.Plus
    )
}

private fun DrawScope.drawGridPattern(
    primaryColor: Color,
    isDark: Boolean
) {
    val gridSize = 50.dp.toPx()
    val alpha = if (isDark) 0.05f else 0.03f
    val gridColor = primaryColor.copy(alpha = alpha)

    // Vertical lines
    var x = 0f
    while (x <= size.width) {
        drawLine(
            color = gridColor,
            start = Offset(x, 0f),
            end = Offset(x, size.height),
            strokeWidth = 1f
        )
        x += gridSize
    }

    // Horizontal lines
    var y = 0f
    while (y <= size.height) {
        drawLine(
            color = gridColor,
            start = Offset(0f, y),
            end = Offset(size.width, y),
            strokeWidth = 1f
        )
        y += gridSize
    }
}

private fun DrawScope.drawPhoneIcon(color: Color) {
    val size = 64f
    val path = Path().apply {
        // Phone outline
        addRoundRect(
            RoundRect(
                left = -size * 0.35f,
                top = -size * 0.5f,
                right = size * 0.35f,
                bottom = size * 0.5f,
                radiusX = 8f,
                radiusY = 8f
            )
        )
    }
    drawPath(path, color, style = Stroke(width = 4f))

    // Home button
    drawCircle(color, radius = 3f, center = Offset(0f, size * 0.35f))
}

private fun DrawScope.drawCpuIcon(color: Color) {
    val size = 64f
    val rectSize = size * 0.4f

    // Main square
    drawRect(
        color = color,
        topLeft = Offset(-rectSize / 2, -rectSize / 2),
        size = androidx.compose.ui.geometry.Size(rectSize, rectSize),
        style = Stroke(width = 4f)
    )

    // Inner square
    val innerSize = rectSize * 0.5f
    drawRect(
        color = color,
        topLeft = Offset(-innerSize / 2, -innerSize / 2),
        size = androidx.compose.ui.geometry.Size(innerSize, innerSize),
        style = Stroke(width = 3f)
    )

    // Pins
    val pinLength = 12f
    val pinOffset = rectSize / 2

    // Top pins
    drawLine(color, Offset(-rectSize * 0.3f, -pinOffset), Offset(-rectSize * 0.3f, -pinOffset - pinLength), strokeWidth = 3f)
    drawLine(color, Offset(rectSize * 0.3f, -pinOffset), Offset(rectSize * 0.3f, -pinOffset - pinLength), strokeWidth = 3f)

    // Bottom pins
    drawLine(color, Offset(-rectSize * 0.3f, pinOffset), Offset(-rectSize * 0.3f, pinOffset + pinLength), strokeWidth = 3f)
    drawLine(color, Offset(rectSize * 0.3f, pinOffset), Offset(rectSize * 0.3f, pinOffset + pinLength), strokeWidth = 3f)

    // Left pins
    drawLine(color, Offset(-pinOffset, -rectSize * 0.25f), Offset(-pinOffset - pinLength, -rectSize * 0.25f), strokeWidth = 3f)
    drawLine(color, Offset(-pinOffset, rectSize * 0.25f), Offset(-pinOffset - pinLength, rectSize * 0.25f), strokeWidth = 3f)

    // Right pins
    drawLine(color, Offset(pinOffset, -rectSize * 0.25f), Offset(pinOffset + pinLength, -rectSize * 0.25f), strokeWidth = 3f)
    drawLine(color, Offset(pinOffset, rectSize * 0.25f), Offset(pinOffset + pinLength, rectSize * 0.25f), strokeWidth = 3f)
}

private fun DrawScope.drawInfinityIcon(color: Color) {
    val size = 64f
    val path = Path().apply {
        moveTo(-size * 0.4f, 0f)
        cubicTo(
            -size * 0.4f, -size * 0.3f,
            -size * 0.2f, -size * 0.3f,
            0f, 0f
        )
        cubicTo(
            size * 0.2f, size * 0.3f,
            size * 0.4f, size * 0.3f,
            size * 0.4f, 0f
        )
        cubicTo(
            size * 0.4f, -size * 0.3f,
            size * 0.2f, -size * 0.3f,
            0f, 0f
        )
        cubicTo(
            -size * 0.2f, size * 0.3f,
            -size * 0.4f, size * 0.3f,
            -size * 0.4f, 0f
        )
    }
    drawPath(path, color, style = Stroke(width = 4f))
}