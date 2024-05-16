package com.github.tatsuyafujisaki.androidplayground.util.datastore

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import androidx.datastore.preferences.protobuf.InvalidProtocolBufferException
import com.github.tatsuyafujisaki.androidplayground.MyPerson
import java.io.InputStream
import java.io.OutputStream

object MyPersonSerializer : Serializer<MyPerson> {
    override val defaultValue: MyPerson = MyPerson.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): MyPerson {
        try {
            return MyPerson.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(
        t: MyPerson,
        output: OutputStream,
    ) = t.writeTo(output)
}
