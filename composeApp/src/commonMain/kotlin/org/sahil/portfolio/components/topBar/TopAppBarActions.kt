package org.sahil.portfolio.components.topBar

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun CompactTopBarAction(
    onMenuClick: () -> Unit,
    icon: ImageVector,
    rotation: Float,
    isDark: Boolean,
    toggleDarkTheme: (Boolean) -> Unit,
) {
    Box(
        modifier = Modifier.size(32.dp).clip(CircleShape)
            .background(MaterialTheme.colorScheme.background)
            .border(BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(0.4f)), CircleShape)
            .clickable(onClick = { toggleDarkTheme(!isDark) }),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            icon,
            null,
            modifier = Modifier.padding(6.dp).size(17.dp).rotate(rotation),
            tint = MaterialTheme.colorScheme.onBackground
        )
    }
    Icon(
        imageVector = Icons.Default.Menu,
        contentDescription = "Menu",
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .size(26.dp)
            .clickable { onMenuClick() },
        tint = MaterialTheme.colorScheme.onBackground
    )
}

@Composable
fun ExpandedTopBarAction(
    icon: ImageVector,
    rotation: Float,
    isDark: Boolean,
    toggleDarkTheme: (Boolean) -> Unit,
    selectedSection: TopAppBarSection,
    onSectionClick: (TopAppBarSection) -> Unit,
    onHireMeClick: () -> Unit
) {
    TopAppBarSection.entries.forEach { section ->
        AnimatedTopBarItem(
            section = section,
            isSelected = section == selectedSection,
            onClick = { onSectionClick(section) }
        )
    }
    Spacer(modifier = Modifier.padding(6.dp))
    Box(
        modifier = Modifier.size(32.dp).clip(CircleShape)
            .background(MaterialTheme.colorScheme.background)
            .border(BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(0.4f)), CircleShape)
            .clickable(onClick = { toggleDarkTheme(!isDark) }),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            icon,
            null,
            modifier = Modifier.padding(6.dp).size(17.dp).rotate(rotation),
            tint = MaterialTheme.colorScheme.onBackground
        )
    }
    Spacer(modifier = Modifier.padding(6.dp))
    TopBarButton(
        onClick = onHireMeClick,
    )
}
