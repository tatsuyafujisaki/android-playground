package com.github.tatsuyafujisaki.androidplayground.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.ImageDecoder
import android.graphics.Matrix
import android.os.Build
import android.provider.MediaStore
import android.view.View
import androidx.core.net.toUri
import java.lang.Integer.max

object GraphicsUtil {
    fun downloadBitmapOrNull(context: Context, url: String): Bitmap? {
        val contentResolver = context.contentResolver
        val uri = url.toUri()

        return runCatching {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                ImageDecoder.decodeBitmap(ImageDecoder.createSource(contentResolver, uri))
            } else {
                MediaStore.Images.Media.getBitmap(contentResolver, uri)
            }
        }.getOrNull()
    }

    private fun calculateInSampleSize(
        originalImageWidth: Int,
        originalImageHeight: Int,
        requiredWidth: Int,
        requiredHeight: Int,
    ): Int {
        var inSampleSize = 1
        // Calculate the largest inSampleSize value that is a power of 2 and keeps both height and width larger than the requested height and width.
        while (requiredWidth * inSampleSize <= originalImageWidth &&
            requiredHeight * inSampleSize <= originalImageHeight
        ) {
            inSampleSize *= 2
        }
        return max(1, inSampleSize / 2)
    }

    fun rotateClockwise(bitmap: Bitmap, degrees: Float): Bitmap = Bitmap.createBitmap(
        bitmap, 0, 0, bitmap.width, bitmap.height,
        Matrix().apply { postRotate(degrees) }, true
    )

    fun takeScreenshot(view: View): Bitmap {
        val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        view.draw(Canvas(bitmap).apply { drawColor(Color.WHITE) })
        return bitmap
    }
}
