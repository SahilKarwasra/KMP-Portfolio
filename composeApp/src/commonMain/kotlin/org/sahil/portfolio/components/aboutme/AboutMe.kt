package org.sahil.portfolio.components.aboutme

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.sahil.portfolio.util.WindowType
import sahil_portfolio.composeapp.generated.resources.Res
import sahil_portfolio.composeapp.generated.resources.aboutme

@Composable
fun AboutMe() {

    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                Text("About ", style = MaterialTheme.typography.displayMedium, color = MaterialTheme.colorScheme.onBackground)
                Text("Me", style = MaterialTheme.typography.displayMedium, color = MaterialTheme.colorScheme.primary)
            }
            Box(modifier = Modifier.width(100.dp).height(3.dp).clip(RoundedCornerShape(2.dp)).background(MaterialTheme.colorScheme.primary))
        }

            BoxWithConstraints{
                val windowType = WindowType.fromWidth(maxWidth)
                val topPadding = when (windowType) {
                    WindowType.Compact -> 40.dp
                    else -> 90.dp
                }
                Column {
                    Spacer(modifier = Modifier.height(topPadding))

                    when (windowType) {
                        WindowType.Compact -> AboutMeCompact()
                        else -> AboutMeExpanded()
                    }
                }

            }
    }
}

@Composable
fun AboutMeExpanded() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(25.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AboutMeImage()
        AboutMeText()
    }
}

@Composable
fun AboutMeCompact() {
    Column(
        modifier = Modifier.fillMaxWidth().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        AboutMeImage(modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(24.dp))
        AboutMeText()
    }
}

@Composable
fun AboutMeImage(
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    val scale by animateFloatAsState(
        targetValue = if (isHovered) 1.02f else 1f,
        animationSpec = tween(450),
        label = "hoverScale"
    )

    val boxBg by animateColorAsState(
        targetValue = if (isHovered) MaterialTheme.colorScheme.primary.copy(0.2f) else Color.Transparent,
        animationSpec = tween(200),
        label = "hoverBg"
    )

    Box {
        Box(modifier = modifier.padding(top = 16.dp, start = 16.dp).border(
            BorderStroke(width = 1.5.dp, color = MaterialTheme.colorScheme.primary), RoundedCornerShape(22.dp)
        ).height(360.dp).fillMaxWidth(0.5f).clip(RoundedCornerShape(22.dp)).background(boxBg))
        Image(
            painter = painterResource(Res.drawable.aboutme),
            contentDescription = null,
            modifier = modifier.graphicsLayer(scaleX = scale, scaleY = scale).height(360.dp).padding(end = 16.dp).fillMaxWidth(0.5f).clip(RoundedCornerShape(22.dp)).clickable(interactionSource = interactionSource, indication = null) {},
            contentScale = ContentScale.Crop
        )
    }

}

@Composable
fun AboutMeText(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(24.dp),
        modifier = modifier.fillMaxWidth(1f)
    ) {
        Text("Mobile App Developer", style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold), color = MaterialTheme.colorScheme.primary)
        Text("I'm Sahil Karwasra, a passionate Mobile App Developer with expertise in creating beautiful, functional, and user-friendly mobile applications. With a strong foundation in Kotlin, Flutter, and various backend technologies, I bring ideas to life through clean code and intuitive design.",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground.copy(0.5f)
        )
        Text("My approach combines technical excellence with creative problem-solving to deliver applications that not only meet but exceed client expectations. I'm constantly learning and adapting to new technologies to stay at the forefront of mobile development.",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground.copy(0.5f)
        )
        Text("When I'm not coding, I enjoy exploring new technologies, contributing to open-source projects, and sharing my knowledge with the developer community",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground.copy(0.5f)
        )
        Column {
            Row {
                AboutMeCard("Location", "Chandigarh, India", modifier = Modifier.weight(1f) )
                Spacer(modifier = Modifier.width(16.dp))
                AboutMeCard("Email", "sahilkarwasra11@gmail.com", modifier = Modifier.weight(1f) )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row {
                AboutMeCard("Phone", "+91 9812408030", modifier = Modifier.weight(1f) )
                Spacer(modifier = Modifier.width(16.dp))
                AboutMeCard("Freelance", "Available", modifier = Modifier.weight(1f) )
            }
        }

    }
}

@Composable
fun AboutMeCard(title: String, description: String, modifier: Modifier = Modifier) {
    Box(modifier = modifier
        .clip(shape = RoundedCornerShape(8.dp))
        .background(color = MaterialTheme.colorScheme.background)
        .border(
            border = BorderStroke(
                width = 0.5.dp,
                color = MaterialTheme.colorScheme.primary.copy(0.5f)
            ),
            shape = RoundedCornerShape(8.dp)
        ),
        contentAlignment = Alignment.TopStart) {
        Column {
            Text(
                title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(start = 12.dp, top = 14.dp)
            )
            Text(
                description,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground.copy(0.5f),
                modifier = Modifier.padding(start = 12.dp, bottom = 14.dp)
            )
        }
    }
}