package org.sahil.portfolio.components.experience

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Smartphone
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Storage
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material.icons.outlined.Dataset
import androidx.compose.material.icons.outlined.Public
import androidx.compose.material.icons.outlined.Smartphone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.sahil.portfolio.util.WindowType


data class Experience(
    val id: String,
    val title: String,
    val company: String,
    val period: String,
    val duration: String,
    val icon: ImageVector,
    val gradientColors: List<Color>,
    val technologies: List<String>,
    val achievements: List<String>? = null,
    val metrics: Map<String, String>
)

@Composable
fun ExperienceSection(
    modifier: Modifier = Modifier,
) {

    val experiences = remember {
        listOf(
            Experience(
                id = "senior-dev",
                title = "Senior Mobile App Developer",
                company = "NS Ventures",
                period = "Feb 2025 - August 2025",
                duration = "7 Months",
                icon = Icons.Default.Smartphone,
                gradientColors = listOf(
                    Color(0xFF34D399),
                    Color(0xFF10B981),
                    Color(0xFF14B8A6)
                ),
                technologies = listOf(
                    "Flutter",
                    "Node.js",
                    "Firebase",
                    "Python",
                    "FastAPI",
                    "UI/UX Design"
                ),
                achievements = listOf(
                    "Led international client projects across Sweden and other countries",
                    "Architected scalable mobile solutions for enterprise clients",
                    "Managed complete app lifecycle from design to deployment",
                    "Delivered high-performance cross-platform applications",
                    "Build Admin Panels for managing the multiple apps",
                    "Worked under the supervision of the Owner of the company"
                ),
                metrics = mapOf(
                    "clients" to "3+ International",
                    "projects" to "5+ Apps",
                    "rating" to "5.0 ★ Client Rating"
                )
            ),
            Experience(
                id = "freelance",
                title = "Freelance Full Stack Mobile App Developer",
                company = "Self-employed",
                period = "Oct 2023 – March 2025",
                duration = "17+ months",
                icon = Icons.Default.Public,
                gradientColors = listOf(
                    Color(0xFF22C55E),
                    Color(0xFF10B981),
                    Color(0xFF14B8A6)
                ),
                technologies = listOf(
                    "Kotlin", "Jetpack Compose", "Compose Multiplatform",
                    "Flutter", "Firebase", "Node.js", "Spring Boot", "FastAPI", "Web Scraping"
                ),
                achievements = listOf(
                    "Started freelance journey with mobile app development",
                    "Mastered Kotlin, Jetpack Compose, and Compose Multiplatform",
                    "Expanded delivery to Android, iOS, Web, and Desktop platforms",
                    "Built real-time apps with Firebase and scalable backends",
                    "Delivered end-to-end full-stack solutions to global clients",
                    "Established long-term client relationships and repeat work"
                ),
                metrics = mapOf(
                    "platforms" to "4 Platforms",
                    "technologies" to "9+ Tech Stack",
                    "growth" to "400% Skill Growth"
                )
            ),
            Experience(
                id = "software-intern",
                title = "Software Developer",
                company = "Technia Infotech",
                period = "Jan 2024 – Feb 2025",
                duration = "1.1 Year",
                icon = Icons.Default.Storage,
                gradientColors = listOf(
                    Color(0xFF2DD4BF),
                    Color(0xFF06B6D4),
                    Color(0xFF3B82F6)
                ),
                technologies = listOf("Kotlin", "Java", "Data Modeling", "MySQL"),
                achievements = listOf(
                    "Designed and optimized relational and NoSQL databases",
                    "Implemented advanced indexing strategies",
                    "Enhanced database security and access controls",
                    "Improved query performance by 40%"
                ),
                metrics = mapOf(
                    "apps" to "Maintained 2 Industry Level Apps",
                    "performance" to "40% Improvement",
                    "security" to "100% Compliance"
                )
            )
        )
    }
    BoxWithConstraints(
        modifier = modifier.fillMaxSize()
    ) {
        val windowType = WindowType.fromWidth(maxWidth)
        Box(
            modifier = modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(24.dp))
                .background(MaterialTheme.colorScheme.primary.copy(0.1f))
        ) {
            BackgroundDecorations()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        horizontal = when (windowType) {
                            WindowType.Compact -> 16.dp
                            WindowType.Expanded -> 48.dp
                        },
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(48.dp))
                ExperienceHeader(windowType)
                Spacer(modifier = Modifier.height(48.dp))
                TimelineOverview(windowType)
                Spacer(modifier = Modifier.height(48.dp))
                experiences.forEach { experience ->
                    ExperienceCard(
                        experience = experience,
                        windowType = windowType
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                }

                Spacer(modifier = Modifier.height(48.dp))
                SkillsMasterySection(windowType)
                Spacer(modifier = Modifier.height(48.dp))
                CallToAction()
                Spacer(modifier = Modifier.height(32.dp))
            }
        }

    }
}

@Composable
fun BackgroundDecorations() {
    val infiniteTransition = rememberInfiniteTransition()

    val rotation1 by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(20000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .size(400.dp)
                .align(Alignment.TopEnd)
                .offset(x = 100.dp, y = (-100).dp)
                .rotate(rotation1)
                .blur(100.dp)
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.15f),
                            Color.Transparent
                        )
                    ),
                    shape = CircleShape
                )
        )

        Box(
            modifier = Modifier
                .size(400.dp)
                .align(Alignment.BottomStart)
                .offset(x = (-100).dp, y = 100.dp)
                .rotate(-rotation1)
                .blur(100.dp)
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.15f),
                            Color.Transparent
                        )
                    ),
                    shape = CircleShape
                )
        )
    }
}

@Composable
fun ExperienceHeader(windowType: WindowType) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        val headingStyle = when (windowType) {
            WindowType.Compact -> MaterialTheme.typography.headlineMedium
            WindowType.Expanded -> MaterialTheme.typography.displayMedium
        }
        Row {
            Text(
                text = "Professional ",
                style = headingStyle,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Journey ",
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
            text = "From freelance beginnings to senior full-stack mobile development leadership",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground.copy(0.7f),
            textAlign = TextAlign.Center,
            modifier = Modifier.widthIn(max = 600.dp)
        )
    }
}

@Composable
fun TimelineOverview(windowType: WindowType) {
    Card(
        modifier = Modifier
            .widthIn(max = 900.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background.copy(alpha = 0.8f)
        ),
        border = BorderStroke(
            1.dp,
            MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(24.dp)
        ) {
            Text(
                text = "Career Timeline Overview",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(32.dp))

            val milestones = listOf(
                Triple("Oct 2023", "Started Journey", "Freelance Career"),
                Triple("Jan 2024", "Software Developer", "Dual Track"),
                Triple("May 2024", "Skill Expansion", "Cross-Platform"),
                Triple("Present", "Senior Developer", "Leading Projects")
            )

            if (windowType == WindowType.Expanded) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    milestones.forEachIndexed { index, milestone ->
                        TimelineMilestone(
                            date = milestone.first,
                            label = milestone.second,
                            description = milestone.third,
                            modifier = Modifier.weight(1f)
                        )
                        if (index < milestones.size - 1) {
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(2.dp)
                                    .background(
                                        Brush.horizontalGradient(
                                            listOf(
                                                Color(0xFF14B8A6),
                                                Color(0xFF10B981),
                                                Color(0xFF34D399)
                                            )
                                        )
                                    )
                            )
                        }
                    }

                }
            } else {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    milestones.forEach { milestone ->
                        TimelineMilestone(
                            date = milestone.first,
                            label = milestone.second,
                            description = milestone.third,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TimelineMilestone(
    date: String,
    label: String,
    description: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .size(16.dp)
                .background(
                    MaterialTheme.colorScheme.primary,
                    shape = CircleShape
                )
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = date,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )

        Text(
            text = description,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun ExperienceCard(
    experience: Experience,
    windowType: WindowType
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    val scale by animateFloatAsState(
        targetValue = if (isHovered) 1.02f else 1f,
        animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing),
        label = "hoverScale"
    )
    val borderColor by animateColorAsState(
        targetValue = if (isHovered) {
            MaterialTheme.colorScheme.primary.copy(alpha = 0.8f)
        } else {
            MaterialTheme.colorScheme.primary.copy(alpha = 0.35f)
        },
        animationSpec = tween(durationMillis = 250),
        label = "borderColor"
    )

    Card(
        modifier = Modifier.widthIn(max = 1120.dp).hoverable(interactionSource).scale(scale),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        border = BorderStroke(
            width = 1.dp,
            borderColor
        )
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .background(Brush.horizontalGradient(experience.gradientColors))
            )

            if (windowType == WindowType.Expanded) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp),
                    horizontalArrangement = Arrangement.spacedBy(32.dp)
                ) {
                    Column(
                        modifier = Modifier.weight(0.35f),
                        verticalArrangement = Arrangement.spacedBy(24.dp)
                    ) {
                        ExperienceRoleInfo(experience)
                        ExperienceMetrics(experience.metrics)
                        ExperienceTechnologies(experience.technologies)
                    }

                    Column(
                        modifier = Modifier.weight(0.65f)
                    ) {
                        ExperienceDetails(experience, windowType)
                    }
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    ExperienceRoleInfo(experience)
                    ExperienceMetrics(experience.metrics)
                    ExperienceTechnologies(experience.technologies)
                    ExperienceDetails(experience, windowType)
                }
            }
        }
    }
}

@Composable
fun ExperienceRoleInfo(experience: Experience) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Brush.linearGradient(experience.gradientColors)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = experience.icon,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(32.dp)
            )
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = experience.title,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Business,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.size(16.dp)
                )
                Text(
                    text = experience.company,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontWeight = FontWeight.Medium
                )
            }

            Surface(
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(14.dp)
                    )
                    Text(
                        text = experience.duration,
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

@Composable
fun ExperienceMetrics(metrics: Map<String, String>) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        metrics.forEach { (key, value) ->
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.05f),
                border = BorderStroke(
                    1.dp,
                    MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = key.replace(Regex("([A-Z])"), " $1")
                            .replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() },
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onBackground.copy(0.85f),
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = value,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun ExperienceTechnologies(technologies: List<String>) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "TECH STACK",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        )

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            technologies.forEach { tech ->
                val interactionSource = remember { MutableInteractionSource() }
                val isHovered by interactionSource.collectIsHoveredAsState()

                val bgColor by animateColorAsState(
                    targetValue = if (isHovered) MaterialTheme.colorScheme.primary.copy(alpha = 0.15f) else MaterialTheme.colorScheme.primary.copy(
                        0.07f
                    ),
                    animationSpec = tween(durationMillis = 200),
                    label = "bgColor"
                )

                val borderAlpha by animateFloatAsState(
                    targetValue = if (isHovered) 0.4f else 0.1f,
                    animationSpec = tween(durationMillis = 200),
                    label = "borderAlpha"
                )
                Surface(
                    modifier = Modifier.hoverable(interactionSource)
                        .pointerHoverIcon(PointerIcon.Hand),
                    shape = RoundedCornerShape(8.dp),
                    color = bgColor,
                    border = BorderStroke(
                        1.dp,
                        MaterialTheme.colorScheme.primary.copy(alpha = borderAlpha)
                    )
                ) {
                    Text(
                        text = tech,
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun ExperienceDetails(
    experience: Experience,
    windowType: WindowType
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        experience.achievements?.let { achievements ->

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.EmojiEvents,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    text = "Key Achievements",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            if (windowType == WindowType.Compact) {
                // 📱 COMPACT → COLUMN
                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    achievements.forEach { achievement ->
                        AchievementCard(
                            achievement = achievement,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            } else {
                // 💻 EXPANDED → FLOW ROW (GRID)
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    achievements.forEach { achievement ->
                        AchievementCard(
                            achievement = achievement,
                            modifier = Modifier.fillMaxWidth(0.48f)
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun AchievementCard(
    achievement: String,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    val bgColor by animateColorAsState(
        targetValue = if (isHovered) MaterialTheme.colorScheme.primary.copy(alpha = 0.1f) else MaterialTheme.colorScheme.primary.copy(
            0.05f
        ),
        animationSpec = tween(durationMillis = 200),
        label = "bgColor"
    )

    val borderAlpha by animateFloatAsState(
        targetValue = if (isHovered) 0.4f else 0.1f,
        animationSpec = tween(durationMillis = 200),
        label = "borderAlpha"
    )

    Surface(
        modifier = modifier.hoverable(interactionSource).pointerHoverIcon(PointerIcon.Hand),
        shape = RoundedCornerShape(12.dp),
        color = bgColor,
        border = BorderStroke(
            1.dp,
            MaterialTheme.colorScheme.primary.copy(alpha = borderAlpha)
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .background(
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(12.dp)
                )
            }
            Text(
                text = achievement,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

data class SkillCategory(
    val title: String,
    val icon: ImageVector,
    val level: String,
    val progress: Int,
    val skills: List<String>,
    val gradientColors: List<Color>,
    val period: String
)

@Composable
fun SkillCard(
    skill: SkillCategory,
    modifier: Modifier = Modifier
) {
    var animateProgress by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        animateProgress = true
    }

    val animatedProgress by animateFloatAsState(
        targetValue = if (animateProgress) skill.progress.toFloat() else 0f,
        animationSpec = tween(durationMillis = 1500, easing = FastOutSlowInEasing)
    )

    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    val borderAlpha by animateFloatAsState(
        targetValue = if (isHovered) 0.7f else 0.1f,
        animationSpec = tween(durationMillis = 200),
        label = "borderAlpha"
    )

    Card(
        modifier = modifier.hoverable(interactionSource),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        border = BorderStroke(
            0.5.dp,
            MaterialTheme.colorScheme.primary.copy(alpha = borderAlpha)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column {
            // Gradient header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp)
                    .background(
                        Brush.horizontalGradient(skill.gradientColors)
                    )
            )

            Column(
                modifier = Modifier.padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Icon and Title
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Brush.linearGradient(skill.gradientColors)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = skill.icon,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    Column {
                        Text(
                            text = skill.title,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "${skill.level} Level",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }

                // Progress Bar
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Proficiency",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Medium
                        )
                        Text(
                            text = "${skill.progress}%",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(animatedProgress / 100f)
                                .fillMaxHeight()
                                .background(
                                    Brush.horizontalGradient(skill.gradientColors)
                                )
                        )
                    }
                }

                // Skills Tags
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    skill.skills.forEach { skillName ->
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.05f),
                            border = androidx.compose.foundation.BorderStroke(
                                1.dp,
                                MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
                            )
                        ) {
                            Text(
                                text = skillName,
                                style = MaterialTheme.typography.labelSmall,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                    }
                }

                // Period
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.CalendarToday,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(12.dp)
                    )
                    Text(
                        text = skill.period,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
fun SkillsMasterySection(windowType: WindowType) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Skills Mastery Journey",
                style = MaterialTheme.typography.displaySmall,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Rapid progression from freelancer to senior developer in 17+ months",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
        }

        val skills = listOf(
            SkillCategory(
                title = "Database Foundation",
                icon = Icons.Outlined.Dataset,
                level = "Advanced",
                progress = 100,
                skills = listOf(
                    "MySQL",
                    "MongoDB",
                    "Optimization",
                    "Security"
                ),
                gradientColors = listOf(
                    Color(0xFF14B8A6),
                    Color(0xFF0891B2)
                ),
                period = "Jan - Aug 2024"
            ),

            SkillCategory(
                title = "Mobile Development",
                icon = Icons.Outlined.Smartphone,
                level = "Senior",
                progress = 100,
                skills = listOf(
                    "Kotlin",
                    "Flutter",
                    "Jetpack Compose",
                    "Firebase"
                ),
                gradientColors = listOf(
                    Color(0xFF22C55E),
                    Color(0xFF059669)
                ),
                period = "Oct 2023 - Present"
            ),

            SkillCategory(
                title = "Full Stack Architecture",
                icon = Icons.Outlined.Public,
                level = "Expert",
                progress = 100,
                skills = listOf(
                    "Spring Boot",
                    "Node.js",
                    "FastAPI",
                    "Cross-Platform"
                ),
                gradientColors = listOf(
                    Color(0xFF10B981),
                    Color(0xFF16A34A)
                ),
                period = "May 2024 - Present"
            )
        )

        if (windowType == WindowType.Expanded) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                skills.forEach { skill ->
                    SkillCard(
                        skill = skill,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        } else {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                skills.forEach { skill ->
                    SkillCard(skill = skill)
                }
            }
        }
    }
}

@Composable
fun CallToAction() {
    Surface(
        shape = RoundedCornerShape(24.dp),
        color = MaterialTheme.colorScheme.primary,
        shadowElevation = 8.dp
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Group,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = "Ready to lead your next project",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimary,
                fontWeight = FontWeight.Medium
            )
        }
    }
}