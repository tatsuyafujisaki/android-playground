package com.github.tatsuyafujisaki.androidplayground.util

import java.io.File
import java.net.URLEncoder

object FileUtil {
    val File.encoded
        get() = URLEncoder.encode(name, "UTF-8")
}
