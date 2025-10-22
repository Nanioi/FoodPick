package com.example.foodpick.presentation.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import com.example.foodpick.ui.theme.Grey

@Composable
fun ClickableDescText(
    label: String,
    value: String,
    style: TextStyle = MaterialTheme.typography.bodyMedium,
    onClick: (() -> Unit)? = null,
) {

    Row {
        Text(
            text = "$label : ",
            style = style.copy(color = Grey),
        )

        Text(
            text = value.ifBlank { "미제공" },
            style = style.copy(
                color = if (onClick != null) Color.Blue else Grey,
                textDecoration = if (onClick != null) TextDecoration.Underline else TextDecoration.None
            ),
            modifier = Modifier
                .weight(1f)
                .then(if (onClick != null) Modifier.clickable { onClick() } else Modifier),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}