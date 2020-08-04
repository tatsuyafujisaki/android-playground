package com.github.tatsuyafujisaki.androidplayground.util

import java.io.File
import java.net.URLEncoder

object FileUtil {
    fun encode(file: File) = URLEncoder.encode(file.name, "UTF-8")
}