package com.example.a2assignment2.utils

import java.text.SimpleDateFormat
import java.util.*

object TimeUtils {
    val formatter = SimpleDateFormat("mm:ss")
    fun getTime(time: Long): String {
        val cv = Calendar.getInstance()
        cv.timeInMillis = time
        return formatter.format(cv.time)
    }
}