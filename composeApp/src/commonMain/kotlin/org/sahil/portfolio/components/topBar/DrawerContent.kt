package org.sahil.portfolio.components.topBar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.FolderOpen
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.MiscellaneousServices
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.WorkOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


@Composable
fun CompactDrawerContent(
    selectedSection: TopAppBarSection,
    onSectionClick: (TopAppBarSection) -> Unit,
    onHireMeClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .width(280.dp)
            .background(MaterialTheme.colorScheme.surface)
            .statusBarsPadding()
            .padding(16.dp)
    ) {
        Row {
            Text(
                "Sahil ",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                "Karwasra",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onBackground,
            )
        }

        Spacer(Modifier.height(16.dp))

        TopAppBarSection.entries.forEach { section ->
            val icon = when (section) {
                TopAppBarSection.Home -> Icons.Outlined.Home
                TopAppBarSection.About -> Icons.Outlined.Person
                TopAppBarSection.Skills -> Icons.Outlined.Build
                TopAppBarSection.Experience -> Icons.Outlined.WorkOutline
                TopAppBarSection.Project -> Icons.Outlined.FolderOpen
                TopAppBarSection.Services -> Icons.Outlined.MiscellaneousServices
                TopAppBarSection.Contact -> Icons.Outlined.Email
            }
            TextButton(
                onClick = { onSectionClick(section) }
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = if (section == selectedSection)
                        MaterialTheme.colorScheme.primary
                    else
                        MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    section.title,
                    color = if (section == selectedSection)
                        MaterialTheme.colorScheme.primary
                    else
                        MaterialTheme.colorScheme.onSurface
                )
            }
        }

        Spacer(Modifier.height(24.dp))

        TopBarButton(
            onClick = onHireMeClick,
            modifier = Modifier.fillMaxWidth()
        )
    }
}





