package com.example.pomodo_task.features.category.data

import androidx.room.TypeConverter
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb

class Converters {
    @TypeConverter
    fun fromColor(color: Color): Int {
        return color.toArgb()
    }

    @TypeConverter
    fun toColor(colorValue: Int): Color {
        return Color(colorValue)
    }
}