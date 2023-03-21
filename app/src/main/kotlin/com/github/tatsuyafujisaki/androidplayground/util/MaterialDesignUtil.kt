package com.github.tatsuyafujisaki.androidplayground.util

import androidx.core.view.children
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

object MaterialDesignUtil {
    fun getChip(cg: ChipGroup, id: Int) = cg.children.filterIsInstance<Chip>().find { it.id == id }
    fun getChips(cg: ChipGroup) = cg.children.filterIsInstance<Chip>()
}
