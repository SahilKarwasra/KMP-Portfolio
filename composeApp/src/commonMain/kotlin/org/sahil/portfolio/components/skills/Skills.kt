package org.sahil.portfolio.components.skills

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.StartOffset
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.Code
import androidx.compose.material.icons.outlined.Dataset
import androidx.compose.material.icons.outlined.PanToolAlt
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.Security
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Smartphone
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sahil.portfolio.util.WindowType
import kotlin.math.PI
import kotlin.math.sin

data class SkillCategory(
    val title: String, val icon: @Composable () -> Unit, val skills: List<String>
)

@Composable
fun SkillsSection(
    modifier: Modifier = Modifier, windowWidth: Dp = 1000.dp
) {
    val windowType = WindowType.fromWidth(windowWidth)

    val skillCategories = remember {
        listOf(
            SkillCategory(
                title = "Programming Languages", icon = {
                    Icon(
                        Icons.Outlined.Code, null, tint = MaterialTheme.colorScheme.primary
                    )
                }, skills = listOf("Kotlin", "Dart", "Java", "Python")
            ), SkillCategory(
                title = "Mobile Development", icon = {
                    Icon(
                        Icons.Outlined.Phone, null, tint = MaterialTheme.colorScheme.primary
                    )
                }, skills = listOf(
                    "Jetpack Compose",
                    "Flutter",
                    "Kotlin Multiplatform",
                    "Push Notifications",
                    "In-App Purchases"
                )
            ), SkillCategory(
                title = "Backend Development", icon = {
                    Icon(
                        Icons.Outlined.Build, null, tint = MaterialTheme.colorScheme.primary
                    )
                }, skills = listOf("Springboot", "FastAPI", "RESTful APIs")
            ), SkillCategory(
                title = "Databases", icon = {
                    Icon(
                        Icons.Outlined.Dataset, null, tint = MaterialTheme.colorScheme.primary
                    )
                }, skills = listOf("MySQL", "MongoDB", "RoomDB")
            ), SkillCategory(
                title = "Cloud Platforms", icon = {
                    Icon(
                        Icons.Outlined.PanToolAlt, null, tint = MaterialTheme.colorScheme.primary
                    )
                }, skills = listOf("AWS", "Firebase", "Supabase")
            ), SkillCategory(
                title = "Specialized Skills", icon = {
                    Icon(
                        Icons.Outlined.Settings, null, tint = MaterialTheme.colorScheme.primary
                    )
                }, skills = listOf(
                    "Real-time data integration", "Authentication systems", "Web Scrapping"
                )
            )
        )
    }
    val hasShown = rememberSaveable("my-skills") {
        mutableStateOf(false)
    }

    val visibilityState = remember {
        MutableTransitionState(false)
    }

    LaunchedEffect(Unit) {
        if (!hasShown.value) {
            hasShown.value = true
            visibilityState.targetState = true
        } else {
            visibilityState.targetState = true
        }
    }

    AnimatedVisibility(
        visibleState = visibilityState,
        enter = fadeIn(animationSpec = tween(700)) + slideInVertically(
            animationSpec = tween(700), initialOffsetY = { 40 })) {

        Box(
            modifier = modifier.fillMaxWidth()
                .padding(vertical = if (windowType == WindowType.Expanded) 96.dp else 64.dp)
        ) {
            Box(
                modifier = Modifier.size(288.dp).offset(x = (-40).dp, y = 100.dp)
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.05f), CircleShape)
                    .blur(120.dp)
            )
            Box(
                modifier = Modifier.size(288.dp).align(Alignment.BottomEnd)
                    .offset(x = 40.dp, y = (-100).dp)
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.05f), CircleShape)
                    .blur(120.dp)
            )

            Column(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Row {
                        Text(
                            "My ",
                            style = MaterialTheme.typography.displayMedium,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Text(
                            "Skills ",
                            style = MaterialTheme.typography.displayMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                    Box(
                        modifier = Modifier.width(100.dp).height(2.dp)
                            .clip(RoundedCornerShape(2.dp))
                            .background(MaterialTheme.colorScheme.primary)
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        "A comprehensive set of technical skills I've developed throughout my career",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onBackground.copy(0.5f)
                    )
                }

                Spacer(modifier = Modifier.height(48.dp))

                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(
                        24.dp, Alignment.CenterHorizontally
                    ),
                    verticalArrangement = Arrangement.spacedBy(24.dp),
                    maxItemsInEachRow = when (windowType) {
                        WindowType.Compact -> 1
                        WindowType.Expanded -> 3
                    }
                ) {
                    skillCategories.forEachIndexed { index, category ->
                        SkillCard(
                            category = category,
                            cardBackground = MaterialTheme.colorScheme.background,
                            modifier = Modifier.weight(1f).widthIn(min = 280.dp, max = 400.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(64.dp))

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Technical Proficiency",
                        style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    FlowRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(
                            24.dp, Alignment.CenterHorizontally
                        ),
                        verticalArrangement = Arrangement.spacedBy(24.dp),
                        maxItemsInEachRow = when (windowType) {
                            WindowType.Compact -> 2
                            WindowType.Expanded -> 4
                        }
                    ) {
                        ProficiencyCard(
                            icon = {
                                Icon(
                                    imageVector = Icons.Outlined.Smartphone,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(40.dp)
                                )
                            },
                            title = "Mobile Development",
                            subtitle = "Native & Cross-platform",
                            delay = 0
                        )
                        ProficiencyCard(
                            icon = {
                                Icon(
                                    imageVector = Icons.Outlined.Build,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(40.dp)
                                )
                            }, title = "Backend Systems", subtitle = "APIs & Databases", delay = 500
                        )
                        ProficiencyCard(
                            icon = {
                                Icon(
                                    imageVector = Icons.Outlined.Star,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(40.dp)
                                )
                            },
                            title = "Performance",
                            subtitle = "Optimization & Speed",
                            delay = 1000
                        )
                        ProficiencyCard(
                            icon = {
                                Icon(
                                    imageVector = Icons.Outlined.Security,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(40.dp)
                                )
                            },
                            title = "Security",
                            subtitle = "Authentication & Data Protection",
                            delay = 1500
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SkillCard(
    category: SkillCategory,
    cardBackground: Color,
    modifier: Modifier = Modifier,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    val hoverOffset by animateFloatAsState(
        targetValue = if (isHovered) -8f else 0f,
        animationSpec = tween(durationMillis = 450, easing = FastOutSlowInEasing),
        label = "hoverOffset"
    )

    val borderAlpha by animateFloatAsState(
        targetValue = if (isHovered) 0.7f else 0.4f,
        animationSpec = tween(durationMillis = 200),
        label = "borderAlpha"
    )

    val iconBg by animateColorAsState(
        targetValue = if (isHovered) MaterialTheme.colorScheme.primary.copy(0.25f) else MaterialTheme.colorScheme.primary.copy(
            0.1f
        ), animationSpec = tween(durationMillis = 200), label = "iconBg"
    )


    Card(modifier = modifier.fillMaxWidth().graphicsLayer {
            translationY = hoverOffset
        }.hoverable(interactionSource = interactionSource).pointerHoverIcon(PointerIcon.Hand),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = cardBackground),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)) {
        Column(
            modifier = Modifier.fillMaxWidth().border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.primary.copy(alpha = borderAlpha),
                    shape = RoundedCornerShape(12.dp)
                ).padding(24.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Box(
                    modifier = Modifier.size(40.dp).background(
                            iconBg, RoundedCornerShape(8.dp)
                        ), contentAlignment = Alignment.Center
                ) {
                    category.icon()
                }

                Spacer(Modifier.width(16.dp))

                Text(
                    text = category.title, fontSize = 20.sp, fontWeight = FontWeight.SemiBold
                )
            }

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                category.skills.forEachIndexed { index, skill ->
                    SkillItem(
                        skill = skill,
                        primary = MaterialTheme.colorScheme.primary,
                        delay = index * 200
                    )
                }
            }
        }
    }
}

@Composable
fun ProficiencyCard(
    icon: @Composable () -> Unit, title: String, subtitle: String, delay: Int
) {
    val transition = rememberInfiniteTransition(label = "cardAnim")

    val phase by transition.animateFloat(
        initialValue = 0f, targetValue = (2f * PI).toFloat(), animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 7000, easing = LinearEasing
            ), initialStartOffset = StartOffset(delay)
        ), label = "phase"
    )

    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    val hoverOffset by animateFloatAsState(
        targetValue = if (isHovered) -8f else 0f,
        animationSpec = tween(durationMillis = 450, easing = FastOutSlowInEasing),
        label = "hoverOffset"
    )

    val borderAlpha by animateFloatAsState(
        targetValue = if (isHovered) 0.7f else 0.4f,
        animationSpec = tween(durationMillis = 200),
        label = "borderAlpha"
    )


    val offsetY = sin(phase) * 4f
    val rotation = sin(phase) * 5f

    Card(
        modifier = Modifier.widthIn(min = 140.dp, max = 260.dp)
            .graphicsLayer { translationY = hoverOffset }.padding(4.dp)
            .hoverable(interactionSource = interactionSource).pointerHoverIcon(PointerIcon.Hand),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        elevation = CardDefaults.cardElevation(0.dp),
        border = BorderStroke(
            1.dp, MaterialTheme.colorScheme.primary.copy(borderAlpha)
        )) {
        Column(
            modifier = Modifier.fillMaxWidth().border(
                    1.dp, Color.Gray.copy(alpha = 0.2f), RoundedCornerShape(8.dp)
                ).padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.graphicsLayer {
                    translationY = offsetY
                    rotationZ = rotation
                }.padding(top = 8.dp)) {
                icon()
            }

            Spacer(Modifier.height(8.dp))

            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center
            )

            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground.copy(0.7f),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

@Composable
fun SkillItem(
    skill: String, primary: Color, delay: Int
) {
    val infiniteTransition = rememberInfiniteTransition()
    val dotScale by infiniteTransition.animateFloat(
        initialValue = 1f, targetValue = 1.5f, animationSpec = infiniteRepeatable(
            animation = tween(3000),
            repeatMode = RepeatMode.Reverse,
            initialStartOffset = StartOffset(delay)
        )
    )

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.size(6.dp).scale(dotScale).background(primary, CircleShape)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = skill, fontSize = 14.sp
        )
    }
}
