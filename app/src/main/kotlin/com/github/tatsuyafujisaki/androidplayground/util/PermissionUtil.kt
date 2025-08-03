package com.github.tatsuyafujisaki.androidplayground.util

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager

@Suppress("unused")
object PermissionUtil {
    private const val REQUEST_EXTERNAL_STORAGE = 0

    val Context.isStoragePermissionGranted
        get() = checkSelfPermission(READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED

    fun Activity.requestStoragePermission() {
        requestPermissions(
            arrayOf(READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE),
            REQUEST_EXTERNAL_STORAGE,
        )
    }
}
