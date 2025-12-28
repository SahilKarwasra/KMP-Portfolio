package org.sahil.portfolio.components.topBar

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Nightlight
import androidx.compose.material.icons.outlined.WbSunny
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.sahil.portfolio.util.WindowType


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    selectedSection: TopAppBarSection,
    onSectionClick: (TopAppBarSection) -> Unit,
    isDark: Boolean,
    toggleDarkTheme: (Boolean) -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
    onHireMeClick: () -> Unit,
    onMenuClick: () -> Unit
) {
    val icon = if (isDark) Icons.Outlined.Nightlight else Icons.Outlined.WbSunny
    val rotation by animateFloatAsState(
        targetValue = if (isDark) -35f else 0f, animationSpec = tween(
            durationMillis = 450,
            easing = FastOutSlowInEasing
        ), label = "themeIconRotation"
    )
    val isScrolled = scrollBehavior.state.contentOffset > 0f
    val containerColor by animateColorAsState(
        targetValue =
            if (!isScrolled)
                MaterialTheme.colorScheme.surface.copy(alpha = 0.75f)
            else
                Color.Transparent,
        label = "topBarColor"
    )
    BoxWithConstraints {
        val windowType = WindowType.fromWidth(maxWidth)

        TopAppBar(
            scrollBehavior = scrollBehavior,
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = containerColor,
                scrolledContainerColor = containerColor
            ),
            title = {
                Row {
                    Text(
                        "Sahil ",
                        style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        "Karwasra",
                        style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                }
            },
            actions = {
                when (windowType) {
                    WindowType.Compact -> {
                        CompactTopBarAction(
                            onMenuClick,
                            icon = icon,
                            rotation = rotation,
                            isDark = isDark,
                            toggleDarkTheme = toggleDarkTheme
                        )
                    }

                    else -> {
                        ExpandedTopBarAction(
                            icon = icon,
                            rotation = rotation,
                            isDark = isDark,
                            toggleDarkTheme = toggleDarkTheme,
                            selectedSection = selectedSection,
                            onSectionClick = onSectionClick,
                            onHireMeClick = onHireMeClick
                        )
                    }
                }
            }
        )

    }
}

