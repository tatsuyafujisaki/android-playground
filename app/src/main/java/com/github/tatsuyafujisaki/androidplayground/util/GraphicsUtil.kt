package com.github.tatsuyafujisaki.androidplayground.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.graphics.Matrix
import android.os.Build
import android.provider.MediaStore
import androidx.core.net.toUri
import com.bumptech.glide.Glide

object GraphicsUtil {
    fun Context.downloadBitmap(url: String): Bitmap? {
        val uri = url.toUri()
        return try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                ImageDecoder.createSource(contentResolver, uri)
                    .let(ImageDecoder::decodeBitmap)
            } else {
                MediaStore.Images.Media.getBitmap(contentResolver, uri)
            }
        } catch (_: Exception) {
            null
        }
    }

    fun Context.downloadBitmap2(url: String) =
        try {
            Glide.with(this)
                .asBitmap()
                .load(url)
                // .error(...) or .fallback(...) does not help if URL is broken.
                // .error(R.drawable.ic_broken_image_black_24dp)
                // .fallback(R.drawable.ic_broken_image_black_24dp)
                .submit()
                .get()
        } catch (_: Exception) {
            null
        }

    fun Bitmap.rotateClockwise(degrees: Float): Bitmap =
        Bitmap.createBitmap(
            this, 0, 0, width, height,
            Matrix().apply { postRotate(degrees) }, true
        )
}
