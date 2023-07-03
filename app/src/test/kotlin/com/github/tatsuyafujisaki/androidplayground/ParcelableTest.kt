package com.github.tatsuyafujisaki.androidplayground

import android.os.Parcel
import android.os.Parcelable
import com.ibm.icu.impl.PluralRulesLoader
import kotlin.test.assertEquals

object ParcelableTest {
    fun <T : Parcelable> assertParcelable(original: T) {
        assertEquals(original, writeAndRead(original))
    }

    private fun <T : Parcelable> writeAndRead(data: T): T = with(Parcel.obtain()) {
        writeParcelable(data, 0)
        setDataPosition(0)
        readParcelable(PluralRulesLoader.loader.javaClass.classLoader)!!
    }
}
