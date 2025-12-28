package org.sahil.portfolio.components.skills

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun Skills() {
    Column {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row {
                Text("My ", style = MaterialTheme.typography.displayMedium, color = MaterialTheme.colorScheme.onBackground)
                Text("Skills ", style = MaterialTheme.typography.displayMedium, color = MaterialTheme.colorScheme.primary)
            }
            Box(modifier = Modifier.width(100.dp).height(3.dp).clip(RoundedCornerShape(2.dp)).background(MaterialTheme.colorScheme.primary))
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                "A comprehensive set of technical skills I've developed throughout my career",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground.copy(0.5f)
            )
        }
        Spacer(modifier = Modifier.height(36.dp))
    }
}