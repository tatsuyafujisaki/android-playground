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
    fun downloadBitmap(context: Context, url: String): Bitmap? {
        val uri = url.toUri()
        return try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                ImageDecoder.createSource(context.contentResolver, uri)
                    .let(ImageDecoder::decodeBitmap)
            } else {
                MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
            }
        } catch (_: Exception) {
            null
        }
    }

    fun downloadBitmap2(context: Context, url: String) =
        try {
            Glide.with(context)
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

    fun rotateClockwise(bitmap: Bitmap, degrees: Float) =
        Bitmap.createBitmap(
            bitmap, 0, 0, bitmap.width, bitmap.height,
            Matrix().apply { postRotate(degrees) }, true
        )
}
