package com.mindinventory.mediaplayerdemo.presentation.model

data class Example(
        var gender: String = "",
        var name: Name? = null,
        var location: Location? = null,
        var email: String = "",
        var login: Login? = null,
        var dob: Dob? = null,
        var phone: String = "",
        var picture: Picture? = null,
        var street: Street? = null)