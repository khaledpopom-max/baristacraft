package com.example.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BilingualHeading(
    textAr: String,
    textEn: String,
    modifier: Modifier = Modifier,
    alignCenter: Boolean = false,
    fontSizeAr: TextUnit = 20.sp,
    fontSizeEn: TextUnit = 14.sp,
    colorAr: Color = MaterialTheme.colorScheme.onSurface,
    colorEn: Color = MaterialTheme.colorScheme.secondary
) {
    Column(
        modifier = modifier,
        horizontalAlignment = if (alignCenter) Alignment.CenterHorizontally else Alignment.Start
    ) {
        Text(
            text = textAr,
            fontSize = fontSizeAr,
            fontWeight = FontWeight.Bold,
            color = colorAr,
            textAlign = if (alignCenter) TextAlign.Center else TextAlign.Start,
            lineHeight = (fontSizeAr.value * 1.3).sp
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = textEn,
            fontSize = fontSizeEn,
            fontWeight = FontWeight.Medium,
            color = colorEn,
            textAlign = if (alignCenter) TextAlign.Center else TextAlign.Start,
            lineHeight = (fontSizeEn.value * 1.3).sp
        )
    }
}

@Composable
fun BilingualBadge(
    textAr: String,
    textEn: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = textAr,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = color
        )
        Text(
            text = " • ",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = color.copy(alpha = 0.6f)
        )
        Text(
            text = textEn,
            fontSize = 11.sp,
            fontWeight = FontWeight.SemiBold,
            color = color
        )
    }
}

@Composable
fun BilingualInline(
    textAr: String,
    textEn: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 14.sp,
    color: Color = MaterialTheme.colorScheme.onSurface,
    maxLines: Int = 1
) {
    Text(
        text = "$textAr / $textEn",
        modifier = modifier,
        fontSize = fontSize,
        fontWeight = FontWeight.SemiBold,
        color = color,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis
    )
}
