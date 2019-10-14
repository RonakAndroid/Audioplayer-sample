package com.mindinventory.mediaplayerdemo.presentation.model

data class Video(
        var id: Int,
        var isPlaying: Boolean,
        var currentTime: String,
        var totalTime: String,
        var filepath: String
)
