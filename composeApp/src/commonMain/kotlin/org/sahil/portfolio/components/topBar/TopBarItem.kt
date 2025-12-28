package org.sahil.portfolio.components.topBar

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedTopBarItem(
    section: TopAppBarSection,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val textColor by animateColorAsState(
        targetValue = if (isSelected)
            MaterialTheme.colorScheme.primary
        else
            MaterialTheme.colorScheme.onSurface,
        label = "textColor"
    )

    val fontWeight by animateIntAsState(
        targetValue = if (isSelected) 700 else 400,
        label = "fontWeight"
    )

    val underlineWidth by animateDpAsState(
        targetValue = if (isSelected) 24.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "underline"
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextButton(onClick = onClick) {
            Text(
                text = section.title,
                color = textColor,
                fontWeight = FontWeight(fontWeight)
            )
        }

        Box(
            modifier = Modifier
                .height(3.dp)
                .width(underlineWidth)
                .background(
                    MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(50)
                )
        )
    }
}