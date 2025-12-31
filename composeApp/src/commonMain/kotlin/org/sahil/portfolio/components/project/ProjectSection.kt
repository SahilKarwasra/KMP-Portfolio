package org.sahil.portfolio.components.project

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Launch
import androidx.compose.material.icons.filled.Code
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.sahil.portfolio.util.WindowType
import sahil_portfolio.composeapp.generated.resources.JwtAuth
import sahil_portfolio.composeapp.generated.resources.Res
import sahil_portfolio.composeapp.generated.resources.crypto
import sahil_portfolio.composeapp.generated.resources.music
import sahil_portfolio.composeapp.generated.resources.rewatch
import sahil_portfolio.composeapp.generated.resources.social
import sahil_portfolio.composeapp.generated.resources.textnow

data class Project(
    val title: String,
    val description: String,
    val image: DrawableResource,
    val tags: List<String>,
    val demoLink: String,
    val githubLink: String
)


@Composable
fun ProjectsSection() {
    BoxWithConstraints {
        val windowType = WindowType.fromWidth(maxWidth)
        val maxWidthDp = maxWidth

        ProjectsContent(
            windowType = windowType,
            maxWidth = maxWidthDp
        )
    }
}

@Composable
private fun ProjectsContent(
    windowType: WindowType,
    maxWidth: Dp
) {
    val projects = remember {
        listOf(
            Project(
                title = "RE-Watch",
                description = "A video streaming app built as my final year project with features like dynamic playback, real-time controls, and a user-focused interface.",
                image = Res.drawable.rewatch,
                tags = listOf("Kotlin", "Jetpack Compose", "FireBase", "DaggerHilt"),
                demoLink = "https://github.com/sahilkarwasra/Re-watchui",
                githubLink = "https://github.com/sahilkarwasra/Re-watchui"
            ),
            Project(
                title = "TextNow",
                description = "A real-time native cross-platform messaging app (Web React, Android Kotlin) that supports instant individual and group chats with a clean and responsive UI.",
                image =Res.drawable.textnow,
                tags = listOf("Kotlin", "React", "Node.js", "Socket.IO", "Ktor", "Koin", "MongoDB"),
                demoLink = "https://github.com/SahilKarwasra/TextNowJetpackCompose",
                githubLink = "https://github.com/SahilKarwasra/TextNowJetpackCompose"
            ),
            Project(
                title = "Crypto Tracker App",
                description = "An app for tracking real-time cryptocurrency prices, trends, and historical data with a smooth user experience.",
                image = Res.drawable.crypto,
                tags = listOf("Compose Multiplatform", "Koin", "Ktor", "Clean Architecture", "MVVM"),
                demoLink = "https://github.com/SahilKarwasra/CryptoTracker",
                githubLink = "https://github.com/SahilKarwasra/CryptoTracker"
            ),
            Project(
                title = "Rythmo Music",
                description = "A music streaming app with offline playback, synced lyrics, and personalized recommendations through an in-app assistant.",
                image = Res.drawable.music,
                tags = listOf("Kotlin", "Jetpack Compose", "Ktor", "Koin", "RestApi", "MVVM"),
                demoLink = "https://github.com/SahilKarwasra/MusicPlayer",
                githubLink = "https://github.com/SahilKarwasra/MusicPlayer"
            ),
            Project(
                title = "JWT Auth + Google OAuth",
                description = "An authentication system using JWT and Google login, providing secure access and token-based session management.",
                image = Res.drawable.JwtAuth,
                tags = listOf("Node.js", "Express.js", "MongoDB", "JWT", "Google Login"),
                demoLink = "https://github.com/SahilKarwasra/Jwt_Auth",
                githubLink = "https://github.com/SahilKarwasra/Jwt_Auth"
            ),
            Project(
                title = "Social",
                description = "A minimalist social media app built with Flutter, featuring photo sharing, user authentication, follow system, and secure Supabase media storage.",
                image = Res.drawable.social,
                tags = listOf("Flutter", "Bloc", "Firebase", "Supabase"),
                demoLink = "https://github.com/SahilKarwasra/social",
                githubLink = "https://github.com/SahilKarwasra/social"
            )
        )
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = if (windowType == WindowType.Expanded) 96.dp else 64.dp)
    ) {
        Box(
            modifier = Modifier
                .size(288.dp)
                .offset(x = maxWidth * 0.75f, y = 100.dp)
                .background(
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.05f),
                    shape = CircleShape
                )
                .blur(120.dp)
        )

        Box(
            modifier = Modifier
                .size(288.dp)
                .offset(x = maxWidth * 0.25f, y = 400.dp)
                .background(
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.05f),
                    shape = CircleShape
                )
                .blur(120.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = if (windowType == WindowType.Expanded) 24.dp else 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val hasAnimated = rememberSaveable("projects_section_animated") {
                mutableStateOf(false)
            }
            val visible = true

            LaunchedEffect(Unit) {
                if (!hasAnimated.value) {
                    hasAnimated.value = true
                }
            }

            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(animationSpec = tween(600)) + slideInVertically(
                    animationSpec = tween(600),
                    initialOffsetY = { 20 }
                )
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(bottom = 48.dp)
                ) {
                    val headingStyle = when (windowType) {
                        WindowType.Compact -> MaterialTheme.typography.headlineMedium
                        WindowType.Expanded -> MaterialTheme.typography.displayMedium
                    }
                    Row {
                        Text(
                            text = "My ",
                            style = headingStyle,
                            color = MaterialTheme.colorScheme.onBackground,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "Projects ",
                            style = headingStyle,
                            color = MaterialTheme.colorScheme.primary,
                            textAlign = TextAlign.Center
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Box(
                        modifier = Modifier.width(100.dp).height(2.dp).clip(RoundedCornerShape(2.dp))
                            .background(MaterialTheme.colorScheme.primary)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "A showcase of my recent work and personal projects",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onBackground.copy(0.7f),
                    )
                }
            }

            val columns = when (windowType) {
                WindowType.Compact -> 1
                WindowType.Expanded -> 3
            }

            val horizontalPadding = if (windowType == WindowType.Expanded) 48.dp else 32.dp
            val gapSize = 32.dp
            val availableWidth = maxWidth - (horizontalPadding * 2)
            val cardWidth = if (columns == 1) {
                availableWidth
            } else {
                (availableWidth - (gapSize * (columns - 1))) / columns
            }

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(32.dp, Alignment.Start),
                verticalArrangement = Arrangement.spacedBy(32.dp),
                maxItemsInEachRow = columns
            ) {
                projects.forEachIndexed { index, project ->
                    val itemVisible = rememberSaveable(project.title) {
                        mutableStateOf(hasAnimated.value)
                    }

                    LaunchedEffect(hasAnimated.value) {
                        if (hasAnimated.value && !itemVisible.value) {
                            delay(100L * index)
                            itemVisible.value = true
                        }
                    }


                    AnimatedVisibility(
                        visible = itemVisible.value,
                        enter = fadeIn(animationSpec = tween(500)) +
                                slideInVertically(
                                    animationSpec = tween(500),
                                    initialOffsetY = { 20 }
                                )
                    ) {
                        ProjectCard(
                            project = project,
                            modifier = Modifier.width(cardWidth)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(48.dp))

            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(animationSpec = tween(600, delayMillis = 600)) +
                        slideInVertically(
                            animationSpec = tween(600, delayMillis = 600),
                            initialOffsetY = { 20 }
                        )
            ) {
                OutlinedButton(
                    onClick = {  },
                    modifier = Modifier.height(48.dp),
                    contentPadding = PaddingValues(horizontal = 24.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Code,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("View More on GitHub")
                }
            }
        }
    }
}

@Composable
private fun ProjectCard(
    project: Project,
    modifier: Modifier = Modifier
) {

    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()


    val elevation by animateDpAsState(
        targetValue = if (isHovered) 16.dp else 2.dp,
        animationSpec = tween(300)
    )
    val offsetY by animateDpAsState(
        targetValue = if (isHovered) (-10).dp else 0.dp,
        animationSpec = tween(300)
    )
    val borderColor by animateColorAsState(
        targetValue = if (isHovered) MaterialTheme.colorScheme.primary.copy(0.5f) else MaterialTheme.colorScheme.primary.copy(0.3f),
        animationSpec = tween(300)
    )

    Card(
        modifier = modifier
            .offset(y = offsetY)
            .hoverable(interactionSource)
            .heightIn(min = 400.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = elevation),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.8f)
        ),
        border = BorderStroke(
            1.dp, borderColor
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(192.dp)
                    .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(project.image),
                    contentDescription = project.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.8f))
                    .padding(24.dp)
            ) {
                Text(
                    text = project.title,
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                    text = project.description,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                    lineHeight = 20.sp,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                FlowRow(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    project.tags.forEach { tag ->
                        val tagInteractionSource = remember { MutableInteractionSource() }
                        val isTagHovered by tagInteractionSource.collectIsHoveredAsState()

                        val stackBgAlpha by animateFloatAsState(
                            targetValue = if (isTagHovered) 0.2f else 0.1f,
                            animationSpec = tween(300)
                        )
                        Surface(
                            modifier = Modifier.hoverable(tagInteractionSource),
                            border = BorderStroke(
                                1.dp, MaterialTheme.colorScheme.primary.copy(stackBgAlpha)
                            ),
                            shape = RoundedCornerShape(6.dp),
                            color = MaterialTheme.colorScheme.primary.copy(stackBgAlpha)
                        ) {
                            Text(
                                text = tag,
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                            )
                        }
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    OutlinedButton(
                        onClick = {  },
                        shape = RoundedCornerShape(6.dp),
                        modifier = Modifier.height(36.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Code,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Code", fontSize = 14.sp)
                    }

                    Button(
                        onClick = {  },
                        modifier = Modifier.height(36.dp),
                        shape = RoundedCornerShape(6.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Launch,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Demo", fontSize = 14.sp)
                    }
                }
            }
        }
    }
}