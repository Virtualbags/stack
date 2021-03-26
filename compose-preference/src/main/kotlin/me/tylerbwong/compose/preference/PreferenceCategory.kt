@file:Suppress("FunctionNaming", "MagicNumber")

package me.tylerbwong.compose.preference

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

fun PreferenceScope.PreferenceCategory(
    name: String,
    labelColor: @Composable () -> Color = { MaterialTheme.colors.secondary },
    divider: @Composable () -> Unit = { Divider() },
    content: PreferenceScope.() -> Unit,
) {
    item {
        Text(
            text = name,
            modifier = Modifier
                .padding(
                    start = 74.dp,
                    top = 20.dp,
                    bottom = 8.dp,
                ),
            color = labelColor(),
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
        )
    }
    content()
    item { divider() }
}
