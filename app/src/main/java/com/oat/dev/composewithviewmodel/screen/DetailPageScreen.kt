package com.oat.dev.composewithviewmodel.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


class DetailPageScreen {
    @Composable
    fun DetailPage() {
        Text(
            text = "Detail page",
            color = Color.Blue,
            fontWeight = FontWeight.Bold,
            fontSize = 48.sp
        )
    }
}