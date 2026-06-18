package com.github.tatsuyafujisaki.androidplayground.data

import androidx.appfunctions.AppFunctionSerializable
import kotlinx.serialization.Serializable

@AppFunctionSerializable(isDescribedByKDoc = true)
@Serializable
data class Album(
    val id: Int,
    val userId: Int? = null,
    val title: String,
)
