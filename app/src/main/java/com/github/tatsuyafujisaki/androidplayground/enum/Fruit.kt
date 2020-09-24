package com.github.tatsuyafujisaki.androidplayground.enum

import androidx.annotation.StringRes
import com.github.tatsuyafujisaki.androidplayground.MainApplication
import com.github.tatsuyafujisaki.androidplayground.R

enum class Fruit(@StringRes val resId: Int) {
    APPLE(R.string.apple),
    ORANGE(R.string.orange);

    override fun toString() =
        MainApplication.instance.applicationContext.getString(resId)
}

fun fruitSampleUsage() {
    for (fruit in Fruit.values()) {
        val x = fruit
        println(fruit.toString())
    }
}
