package com.github.tatsuyafujisaki.androidplayground.util

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.graphics.Matrix
import android.os.Build
import android.provider.MediaStore
import androidx.annotation.DrawableRes
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import java.lang.Integer.max

object GraphicsUtil {
    @Suppress("DEPRECATION")
    fun Context.downloadBitmapOrNull(url: String): Bitmap? {
        return runCatching {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                ImageDecoder.decodeBitmap(ImageDecoder.createSource(contentResolver, url.toUri()))
            } else {
                MediaStore.Images.Media.getBitmap(contentResolver, url.toUri())
            }
        }.getOrNull()
    }

    fun Context.downloadBitmapOrNull2(url: String) = runCatching {
        Glide.with(this)
            .asBitmap()
            .load(url)
            // .error(...) or .fallback(...) does not help if URL is broken.
            // .error(R.drawable.ic_broken_image_black_24dp)
            // .fallback(R.drawable.ic_broken_image_black_24dp)
            .submit()
            .get()
    }.getOrNull()

    // https://developer.android.com/topic/performance/graphics/load-bitmap
    private fun Resources.decodeSampledBitmapFromResource(
        @DrawableRes drawableResId: Int,
        requiredWidth: Int,
        requiredHeight: Int
    ) = with(BitmapFactory.Options()) {
        inJustDecodeBounds = true
        BitmapFactory.decodeResource(this@decodeSampledBitmapFromResource, drawableResId, this)
        inSampleSize = calculateInSampleSize(outWidth, outHeight, requiredWidth, requiredHeight)
        inJustDecodeBounds = false
        BitmapFactory.decodeResource(this@decodeSampledBitmapFromResource, drawableResId, this)
    }

    private fun calculateInSampleSize(
        originalImageWidth: Int,
        originalImageHeight: Int,
        requiredWidth: Int,
        requiredHeight: Int
    ): Int {
        var inSampleSize = 1
        // Calculate the largest inSampleSize value that is a power of 2 and keeps both height and width larger than the requested height and width.
        while (requiredWidth * inSampleSize <= originalImageWidth &&
            requiredHeight * inSampleSize <= originalImageHeight) {
            inSampleSize *= 2
        }
        return max(1, inSampleSize / 2)
    }

    fun Bitmap.rotateClockwise(degrees: Float): Bitmap =
        Bitmap.createBitmap(
            this, 0, 0, width, height,
            Matrix().apply { postRotate(degrees) }, true
        )
}
