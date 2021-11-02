package com.example.testkotlin.models

import com.example.testkotlin.R
import java.io.Serializable

enum class FighterType : Serializable {
    MMA,
    Boxing;

    fun getIconSource(): Int {
        return when (this) {
            MMA -> R.drawable.ic_mma
            Boxing -> R.drawable.ic_boxing
        }
    }
}