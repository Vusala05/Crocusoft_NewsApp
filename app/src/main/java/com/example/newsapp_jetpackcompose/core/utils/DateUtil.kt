package com.example.newsapp_jetpackcompose.core.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate

object DateUtil {

    fun getCurrentFormattedDate(): String {
        val date = LocalDate.now()
        val day = date.dayOfMonth
        val suffix = getDaySuffix(day)

        val month = date.month.name.lowercase()
            .replaceFirstChar { it.uppercase() }

        return "$month $day$suffix, ${date.year}"
    }

    private fun getDaySuffix(day: Int): String {
        return when {
            day in 11..13 -> "th"
            day % 10 == 1 -> "st"
            day % 10 == 2 -> "nd"
            day % 10 == 3 -> "rd"
            else -> "th"
        }
    }
}