package com.github.tatsuyafujisaki.androidplayground.util

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.ImageDecoder
import android.graphics.Matrix
import android.graphics.Paint
import android.os.Build
import android.provider.MediaStore
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.core.graphics.createBitmap
import androidx.core.graphics.drawable.toBitmapOrNull
import androidx.core.net.toUri
import java.lang.Integer.max

object GraphicsUtil {
    object Converter {
        private fun convertDrawableResToBitmapOrNull1(
            resources: Resources,
            @DrawableRes id: Int,
        ) = BitmapFactory.decodeResource(resources, id)

        fun convertDrawableResToBitmapOrNull2(
            context: Context,
            @DrawableRes id: Int,
        ) = AppCompatResources.getDrawable(context, id)?.toBitmapOrNull()

        @Composable
        fun convertDrawableResToImageVector(
            @DrawableRes id: Int,
        ) = ImageVector.vectorResource(id = id)
    }

    fun downloadBitmapOrNull(
        context: Context,
        url: String,
    ): Bitmap? {
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

    fun rotateClockwise(
        bitmap: Bitmap,
        degrees: Float,
    ): Bitmap =
        Bitmap.createBitmap(
            bitmap,
            0,
            0,
            bitmap.width,
            bitmap.height,
            Matrix().apply { postRotate(degrees) },
            true,
        )

    fun takeScreenshot(view: View): Bitmap {
        val bitmap = createBitmap(view.width, view.height)
        view.draw(Canvas(bitmap).apply { drawColor(Color.WHITE) })
        return bitmap
    }

    fun drawCircleAsBitmap(
        radius: Float = 100f,
        @ColorInt circleColor: Int = Color.RED,
        @ColorInt backgroundColor: Int? = Color.WHITE,
    ): Bitmap {
        val diameter = 2 * radius.toInt()
        val bitmap = createBitmap(diameter, diameter)
        val paint = Paint().apply { color = circleColor }
        with(Canvas(bitmap)) {
            backgroundColor?.let { drawColor(it) }
            drawCircle(radius, radius, radius, paint)
        }
        return bitmap
    }

    fun drawMouseBitmap(
        radius: Float = 100f,
        @ColorInt faceColor: Int = Color.RED,
        @ColorInt backgroundColor: Int? = Color.WHITE,
    ): Bitmap {
        val canvasSize = 5 * radius.toInt()
        val bitmap = createBitmap(canvasSize, canvasSize)
        val paint =
            Paint().apply {
                color = faceColor
            }
        val leftEarX = canvasSize * 0.3f
        val rightEarX = canvasSize - leftEarX
        val earY = canvasSize * 0.3f
        val earRadius = radius * 0.7f
        with(Canvas(bitmap)) {
            backgroundColor?.let { drawColor(it) }
            drawCircle(canvasSize * 0.5f, canvasSize * 0.5f, radius, paint)
            drawCircle(leftEarX, earY, earRadius, paint)
            drawCircle(rightEarX, earY, earRadius, paint)
        }
        return bitmap
    }
}
