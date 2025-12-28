package org.sahil.portfolio.components.topBar

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TopBarButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = onClick,
        modifier = modifier.padding(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 2.dp),
        shape = RoundedCornerShape(8.dp),
    ) {
        Text("Hire Me")
    }
}

