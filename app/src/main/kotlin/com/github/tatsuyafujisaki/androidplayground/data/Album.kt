package com.github.tatsuyafujisaki.androidplayground.data

import androidx.appfunctions.AppFunctionSerializable
import kotlinx.serialization.Serializable

@AppFunctionSerializable(isDescribedByKDoc = true)
@Serializable
data class Album(
    /** The unique identifier of the album. */
    val id: Int,
    /** The ID of the user who owns the album. */
    val userId: Int? = null,
    /** The title of the album. */
    val title: String,
)
