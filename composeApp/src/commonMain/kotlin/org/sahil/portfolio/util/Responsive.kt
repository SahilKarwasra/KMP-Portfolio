package org.sahil.portfolio.util

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

enum class WindowType {
    Compact,
//    Medium,
    Expanded;

    companion object{
        fun fromWidth(width: Dp): WindowType{
            return when {
                width < 900.dp -> Compact
//                width < 867.dp -> Medium
                else -> Expanded
            }
        }
    }
}
