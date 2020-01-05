package com.github.tatsuyafujisaki.androidplayground.util

import android.graphics.Bitmap
import android.graphics.Matrix

object GraphicsUtils {
    fun rotateClockwise(bitmap: Bitmap, degrees: Float) =
            Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height,
                    Matrix().apply { postRotate(degrees) }, true)
}