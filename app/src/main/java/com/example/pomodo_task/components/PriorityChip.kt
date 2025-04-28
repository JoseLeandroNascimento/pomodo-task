package com.example.pomodo_task.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pomodo_task.R
import com.example.pomodo_task.ui.theme.Gray300

@Composable
fun PriorityChip(modifier: Modifier = Modifier) {

    Box(
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Image(
                modifier = Modifier
                    .size(15.dp),
                painter = painterResource(id = R.drawable.emoji_flat_cold_face),
                contentDescription = null
            )
            Text(
                buildAnnotatedString {
                    append("Prioridade")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(" baixa")
                    }
                },
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = if (isSystemInDarkTheme()) Color.White else Gray300
            )
        }
    }
}

@Preview
@Composable
private fun PriorityChipPreview() {

    PriorityChip(modifier = Modifier)
}