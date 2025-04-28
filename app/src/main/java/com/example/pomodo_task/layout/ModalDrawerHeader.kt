package com.example.pomodo_task.layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pomodo_task.R
import com.example.pomodo_task.ui.theme.Gray400
import com.example.pomodo_task.ui.theme.Green300

@Composable
fun ModalDrawerHeader(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(112.dp)
            .background(color = Green300)
    ) {

        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Image(
                modifier = Modifier.size(90.dp),
                painter = painterResource(id = R.drawable.emoji_noto_hot_face),
                contentDescription = null
            )
            Text(
                text = "PomodoList",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = if (isSystemInDarkTheme()) Gray400 else Color.White
            )
        }

    }
}

@Preview
@Composable
private fun ModalDrawerHeaderPreview() {

    ModalDrawerHeader(modifier = Modifier)

}