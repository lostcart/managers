package com.lost.domain.models

data class Manager(
    val firstName: String,
    val lastName: String,
    var email: String? = null
)