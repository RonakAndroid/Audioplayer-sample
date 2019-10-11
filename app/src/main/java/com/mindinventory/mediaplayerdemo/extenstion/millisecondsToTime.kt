package com.mindinventory.mediaplayerdemo.extenstion

fun millisecondsToTime(milliseconds: Long): String {
        val minutes = milliseconds / 1000 / 60
        val seconds = milliseconds / 1000 % 60
        val secondsStr = java.lang.Long.toString(seconds)
        val secs: String
        if (secondsStr.length >= 2) {
            secs = secondsStr.substring(0, 2)
        } else {
            secs = "0$secondsStr"
        }

        return "$minutes:$secs"
    }