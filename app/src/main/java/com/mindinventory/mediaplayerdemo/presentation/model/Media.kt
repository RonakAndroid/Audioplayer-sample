package com.mindinventory.mediaplayerdemo.presentation.model

data class Media(
        var id: Int,
        var isPlaying: Boolean,
        var currentTime: String,
        var totalTime: String,
        var filepath: String
)
