package com.example.pomodo_task.features.category.model

import androidx.annotation.DrawableRes
import com.example.pomodo_task.R

enum class Priority(
    val description: String,
    val label: String,
    @DrawableRes val  icon: Int,
    val index: Int
) {

    BAIXA("BAIXA", "Baixa", R.drawable.emoji_flat_cold_face, 1),
    NORMAL("NORMAL", "Normal", R.drawable.emoji_slightly_smiling_face, 2),
    ALTA("ALTA", "Alta", R.drawable.emoji_noto_hot_face, 3);

    companion object{
        fun getPriorityByIndex(index:Int): Priority? {
            return entries.find {
                it.index == index
            }
        }
    }

}