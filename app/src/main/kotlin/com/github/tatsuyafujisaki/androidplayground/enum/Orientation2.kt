package com.github.tatsuyafujisaki.androidplayground.enum

enum class Orientation2 {
    PORTRAIT,
    LANDSCAPE,
    ;

    companion object {
        fun create(orientation4: Orientation4) =
            when (orientation4) {
                Orientation4.PORTRAIT, Orientation4.REVERSE_PORTRAIT -> PORTRAIT
                Orientation4.LANDSCAPE, Orientation4.REVERSE_LANDSCAPE -> LANDSCAPE
            }
    }
}
