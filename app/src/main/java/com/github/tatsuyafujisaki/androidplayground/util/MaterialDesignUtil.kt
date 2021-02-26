package com.github.tatsuyafujisaki.androidplayground.util

import androidx.core.view.children
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

object MaterialDesignUtil {
    val ChipGroup.chips
        get() = children.filterIsInstance<Chip>()

    fun ChipGroup.getChip(id: Int) =
        children.filterIsInstance<Chip>().firstOrNull { it.id == id }
}
