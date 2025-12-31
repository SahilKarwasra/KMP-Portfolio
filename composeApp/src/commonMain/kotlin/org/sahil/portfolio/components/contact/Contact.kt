package org.sahil.portfolio.components.contact

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import org.sahil.portfolio.components.footer.FooterSection
import org.sahil.portfolio.components.hero.HeroSocialIcons
import org.sahil.portfolio.util.WindowType
import sahil_portfolio.composeapp.generated.resources.Res
import sahil_portfolio.composeapp.generated.resources.github
import sahil_portfolio.composeapp.generated.resources.instagram
import sahil_portfolio.composeapp.generated.resources.linkedin

data class ContactInfo(
    val title: String,
    val primaryText: String,
    val secondaryText: String? = null,
    val icon: ImageVector,
    val actionLabel: String,
    val actionUrl: String
)

data class SocialLink(
    val platform: String,
    val url: String,
    val icon: ImageVector
)

@Composable
fun ContactSection(modifier: Modifier = Modifier) {
    val uriHandler = LocalUriHandler.current

    val contactInfo = remember {
        listOf(
            ContactInfo(
                title = "Email",
                primaryText = "sahilkarwasra11@gmail.com",
                icon = Icons.Default.Email,
                actionLabel = "Send Email",
                actionUrl = "mailto:sahilkarwasra11@gmail.com"
            ),
            ContactInfo(
                title = "Phone",
                primaryText = "India: +91 9812408030",
                icon = Icons.Default.Phone,
                actionLabel = "Contact Me",
                actionUrl = "tel:+919812408030"
            ),
            ContactInfo(
                title = "Location",
                primaryText = "Chandigarh, India",
                icon = Icons.Default.LocationOn,
                actionLabel = "View on Map",
                actionUrl = "https://maps.google.com/?q=Chandigarh,India"
            )
        )
    }

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
            AnimatedBlob(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(x = 40.dp, y = (-40).dp)
                    .size(280.dp),
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.05f)
            )

            AnimatedBlob(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .offset(x = (-40).dp, y = 40.dp)
                    .size(300.dp),
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.05f),
                delay = 1500
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = containerPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Header Section
                ContactHeader(windowType)

                Spacer(modifier = Modifier.height(48.dp))

                // Contact Info Cards
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterHorizontally),
                    verticalArrangement = Arrangement.spacedBy(24.dp),
                    maxItemsInEachRow = when (windowType) {
                        WindowType.Compact -> 1
                        WindowType.Expanded -> 3
                    }
                ) {
                    contactInfo.forEachIndexed { index, info ->
                        ContactCard(
                            contactInfo = info,
                            index = index,
                            windowType = windowType,
                            onClick = { uriHandler.openUri(info.actionUrl) }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(48.dp))

                // Social Links Section
                SocialLinksSection(
                    onSocialClick = { url -> uriHandler.openUri(url) }
                )

                Spacer(modifier = Modifier.height(64.dp))

                // Call to Action Card
                CTACard(
                    windowType = windowType,
                    onResumeClick = {
                        // Replace with your actual resume URL
                        uriHandler.openUri("https://your-resume-url.com")
                    }
                )
                Spacer(modifier = Modifier.height(64.dp))

                HorizontalDivider()
                Spacer(modifier = Modifier.height(64.dp))

                FooterSection()

            }
        }
    }
}

@Composable
private fun ContactHeader(windowType: WindowType) {
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
                text = "Get IN ",
                style = headingStyle,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Touch ",
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
            text = "Feel free to reach out for collaborations, opportunities, or just to say hello!",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = alpha),
            textAlign = TextAlign.Center,
            modifier = Modifier.widthIn(max = 600.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ContactCard(
    contactInfo: ContactInfo,
    index: Int,
    windowType: WindowType,
    onClick: () -> Unit
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

    val border by animateColorAsState(
        targetValue = if (isHovered) MaterialTheme.colorScheme.primary.copy(0.5f) else MaterialTheme.colorScheme.primary.copy(0.3f),
        animationSpec = tween(300)
    )

    val cardWidth = when (windowType) {
        WindowType.Compact -> Modifier.fillMaxWidth()
        WindowType.Expanded -> Modifier.width(320.dp)
    }

    Card(
        modifier = cardWidth
            .hoverable(interactionSource)
            .offset(y = offsetY)
            .scale(scale)
            .border(1.dp, border, RoundedCornerShape(16.dp))
            .height(260.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.8f)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = elevation),
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface.copy(0.8f))
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Icon Box
            AnimatedIconBox(
                icon = contactInfo.icon,
                isHovered = isHovered
            )

            // Title
            Text(
                text = contactInfo.title,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = alpha),
                textAlign = TextAlign.Center
            )

            // Contact Details
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = contactInfo.primaryText,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = alpha),
                    textAlign = TextAlign.Center
                )
                contactInfo.secondaryText?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = alpha),
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // Action Button
            AnimatedActionButton(
                label = contactInfo.actionLabel,
                icon = contactInfo.icon,
                onClick = onClick
            )
        }
    }
}

@Composable
private fun AnimatedIconBox(
    icon: ImageVector,
    isHovered: Boolean
) {
    val backgroundColor by animateColorAsState(
        targetValue = if (isHovered)
            MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
        else
            MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
        animationSpec = tween(300)
    )

    val scale by animateFloatAsState(
        targetValue = if (isHovered) 1.1f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    Box(
        modifier = Modifier
            .size(64.dp)
            .scale(scale)
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
private fun AnimatedActionButton(
    label: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    var isPressed by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.95f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        )
    )

    OutlinedButton(
        onClick = {
            isPressed = true
            onClick()
        },
        modifier = Modifier.scale(scale),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Composable
private fun SocialLinksSection(
    onSocialClick: (String) -> Unit
) {
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(400)
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

    Column(
        modifier = Modifier.offset(y = offsetY),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Connect With Me",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.SemiBold
            ),
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = alpha)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row (
            horizontalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterHorizontally),
        ) {
                HeroSocialIcons(Res.drawable.github, 24.dp, onClick = {onSocialClick("https://github.com/sahilkarwasra")})
                HeroSocialIcons(Res.drawable.linkedin,24.dp, onClick = {onSocialClick("https://linkedin.com/in/sahilkarwasra")})
                HeroSocialIcons(Res.drawable.instagram,24.dp, onClick = {onSocialClick("https://instagram.com/karwasra_sahil11")})
        }
    }
}

@Composable
private fun SocialIconButton(
    icon: ImageVector,
    onClick: () -> Unit
) {
    var isPressed by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.9f else 1f,
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
            .size(56.dp)
            .scale(scale)
            .clip(CircleShape)
            .background(backgroundColor)
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
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = MaterialTheme.colorScheme.primary
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
private fun CTACard(
    windowType: WindowType,
    onResumeClick: () -> Unit
) {
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
                        fontWeight = FontWeight.Bold,
                        fontSize = when (windowType) {
                            WindowType.Compact -> 24.sp
                            WindowType.Expanded -> 32.sp
                        }
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
                    modifier = Modifier.widthIn(max = 700.dp),
                    lineHeight = 24.sp
                )

                Spacer(modifier = Modifier.height(32.dp))

                DownloadResumeButton(onClick = onResumeClick)
            }
        }
    }
}

@Composable
private fun DownloadResumeButton(onClick: () -> Unit) {
    var isPressed by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.95f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        )
    )

    Button(
        onClick = {
            isPressed = true
            onClick()
        },
        modifier = Modifier
            .scale(scale)
            .height(56.dp)
            .widthIn(min = 220.dp),
        shape = RoundedCornerShape(28.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 4.dp,
            pressedElevation = 8.dp
        )
    ) {
        Icon(
            imageVector = Icons.Default.Download,
            contentDescription = null,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = "Download Resume",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.SemiBold
            )
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
private fun AnimatedBlob(
    modifier: Modifier = Modifier,
    color: Color,
    delay: Long = 0
) {
    val infiniteTransition = rememberInfiniteTransition()

    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.3f,
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