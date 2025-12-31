package org.sahil.portfolio.components.myServices

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.draw.rotate
import org.sahil.portfolio.util.WindowType

data class Service(
    val title: String,
    val description: String,
    val icon: ImageVector
)

@Composable
fun ServicesSection(modifier: Modifier = Modifier) {
    val services = remember {
        listOf(
            Service(
                "Mobile App Development",
                "Building native and cross-platform mobile applications with Kotlin, Java, Flutter, and React Native.",
                Icons.Default.PhoneAndroid
            ),
            Service(
                "Responsive UI Design",
                "Creating beautiful, intuitive, and responsive user interfaces that work across all screen sizes.",
                Icons.Default.Layers
            ),
            Service(
                "Backend Integration",
                "Connecting mobile applications to backend services, APIs, and databases for seamless data flow.",
                Icons.Default.Storage
            ),
            Service(
                "Database Design",
                "Designing efficient database structures for optimal data storage, retrieval, and management.",
                Icons.Default.AccountTree
            ),
            Service(
                "API Development",
                "Building robust RESTful APIs and GraphQL endpoints for mobile and web applications.",
                Icons.Default.Code
            ),
            Service(
                "App Maintenance & Updates",
                "Providing ongoing support, maintenance, and feature updates for existing applications.",
                Icons.Default.Refresh
            ),
            Service(
                "Authentication Systems",
                "Implementing secure authentication and authorization systems for user management.",
                Icons.Default.Shield
            ),
            Service(
                "Performance Optimization",
                "Optimizing application performance for speed, efficiency, and battery consumption.",
                Icons.Default.FormatBold
            )
        )
    }

    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 64.dp)
    ) {
        val windowType = WindowType.fromWidth(maxWidth)
        val containerPadding = when (windowType) {
            WindowType.Compact -> 16.dp
            WindowType.Expanded -> 48.dp
        }

        Box(modifier = Modifier.fillMaxWidth()) {
            // Animated background blobs
            AnimatedBlob(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(x = 40.dp, y = (-80).dp)
                    .size(300.dp),
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.05f)
            )

            AnimatedBlob(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .offset(x = (-40).dp, y = 80.dp)
                    .size(300.dp),
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.05f),
                delay = 1000
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = containerPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Header Section
                ServiceHeader(windowType)

                Spacer(modifier = Modifier.height(48.dp))

                // Services Grid
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterHorizontally),
                    verticalArrangement = Arrangement.spacedBy(24.dp),
                    maxItemsInEachRow = when (windowType) {
                        WindowType.Compact -> 1
                        WindowType.Expanded -> 4
                    }
                ) {
                    services.forEachIndexed { index, service ->
                        ServiceCard(
                            service = service,
                            index = index,
                            windowType = windowType
                        )
                    }
                }

                Spacer(modifier = Modifier.height(64.dp))

                // Call to Action
                CallToActionCard(windowType = windowType)
            }
        }
    }
}

@Composable
private fun ServiceHeader(windowType: WindowType) {
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(100)
        visible = true
    }

    val offsetY by animateDpAsState(
        targetValue = if (visible) 0.dp else 20.dp,
        animationSpec = tween(600)
    )

    val alpha by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(600)
    )

    val headingStyle = when (windowType) {
        WindowType.Compact -> MaterialTheme.typography.headlineMedium
        WindowType.Expanded -> MaterialTheme.typography.displayMedium
    }

    Column(
        modifier = Modifier.offset(y = offsetY),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            Text(
                text = "My ",
                style = headingStyle,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Services ",
                style = headingStyle,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(16.dp))


        Box(
            modifier = Modifier.width(100.dp).height(2.dp).clip(RoundedCornerShape(2.dp))
                .background(MaterialTheme.colorScheme.primary)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Professional services I offer to help bring your ideas to life",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = alpha),
            textAlign = TextAlign.Center,
            modifier = Modifier.widthIn(max = 600.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ServiceCard(
    service: Service,
    index: Int,
    windowType: WindowType
) {
    var visible by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    LaunchedEffect(Unit) {
        delay(100L * index)
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

    val elevation by animateDpAsState(
        targetValue = if (isHovered) 8.dp else 1.dp,
        animationSpec = tween(300)
    )

    val scale by animateFloatAsState(
        targetValue = if (isHovered) 1.02f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    val cardWidth = when (windowType) {
        WindowType.Compact -> Modifier.fillMaxWidth()
        WindowType.Expanded -> Modifier.width(280.dp)
    }

    val borderColor by animateColorAsState(
        targetValue = if (isHovered) MaterialTheme.colorScheme.primary.copy(0.5f) else MaterialTheme.colorScheme.primary.copy(0.3f),
        animationSpec = tween(300)
    )

    Card(
        modifier = cardWidth
            .hoverable(interactionSource)
            .offset(y = offsetY)
            .scale(scale)
            .border(
                width = 1.2.dp,
                color = borderColor,
                shape = RoundedCornerShape(16.dp)
            )
            .height(240.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.8f)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = elevation),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface.copy(0.8f))
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AnimatedIconBox(
                icon = service.icon,
                isHovered = isHovered
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = service.title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = alpha),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = service.description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = alpha),
                textAlign = TextAlign.Center,
                lineHeight = 18.sp
            )
        }
    }
}

@Composable
private fun AnimatedIconBox(
    icon: ImageVector,
    isHovered: Boolean
) {

    val scale by animateFloatAsState(
        targetValue = if (isHovered) 1.1f else 1f,
        animationSpec = tween(300)
    )

    val rotation by animateFloatAsState(
        targetValue = if (isHovered) 12f else 0f,
        animationSpec = tween(300)
    )

    val backgroundColor by animateColorAsState(
        targetValue = if (isHovered)
            MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
        else
            MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
        animationSpec = tween(300)
    )

    Box(
        modifier = Modifier
            .size(64.dp)
            .scale(scale)
            .rotate(rotation)
            .clip(CircleShape)
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(32.dp),
            tint = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
private fun CallToActionCard(windowType: WindowType) {
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(600)
        visible = true
    }

    val offsetY by animateDpAsState(
        targetValue = if (visible) 0.dp else 20.dp,
        animationSpec = tween(600)
    )

    val alpha by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(600)
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .offset(y = offsetY),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.8f)
        )
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            // Background blobs
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(x = (-50).dp, y = (-50).dp)
                    .size(200.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.05f))
                    .blur(60.dp)
            )

            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .offset(x = (-50).dp, y = 50.dp)
                    .size(200.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.05f))
                    .blur(60.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = when (windowType) {
                            WindowType.Compact -> 24.dp
                            WindowType.Expanded -> 64.dp
                        },
                        vertical = 48.dp
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Ready to bring your app idea to life?",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = alpha),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "I'm available for freelance projects and full-time opportunities. Let's discuss how I can help you create an exceptional mobile experience.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = alpha),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.widthIn(max = 700.dp)
                )

                Spacer(modifier = Modifier.height(32.dp))

                AnimatedButton()
            }
        }
    }
}

@Composable
private fun AnimatedButton() {
    var isPressed by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.95f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        )
    )

    Button(
        onClick = { isPressed = !isPressed },
        modifier = Modifier
            .scale(scale)
            .height(56.dp)
            .widthIn(min = 200.dp),
        shape = RoundedCornerShape(28.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 4.dp,
            pressedElevation = 8.dp
        )
    ) {
        Text(
            text = "Get in Touch",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.SemiBold
            )
        )
    }
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
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(4000, delayMillis = delay.toInt()),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(
        modifier = modifier
            .scale(scale)
            .clip(CircleShape)
            .background(color)
            .blur(80.dp)
    )
}