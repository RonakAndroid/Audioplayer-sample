package com.mindinventory.mediaplayerdemo.presentation.model

data class Login(
        var uuid: String = "",
        var username: String = "",
        var password: String = "",
        var salt: String = "",
        var md5: String = "",
        var sha1: String = "",
        var sha256: String = "")
