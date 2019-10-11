package com.mindinventory.mediaplayerdemo.presentation.model

data class Media(
    var id: Int = 0,
    var isPlaying: Boolean = false,
    var currentTime: String,
    var totalTime: String,
    var filepath: String
)
