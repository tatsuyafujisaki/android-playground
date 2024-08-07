package com.github.tatsuyafujisaki.androidplayground.data

import kotlinx.serialization.Serializable

@Serializable
data class Album(
    val id: Int,
    val userId: Int? = null,
    val title: String,
)
