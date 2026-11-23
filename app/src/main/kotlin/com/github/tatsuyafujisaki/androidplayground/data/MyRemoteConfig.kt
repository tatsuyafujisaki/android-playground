package com.github.tatsuyafujisaki.androidplayground.data

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.format.char
import kotlinx.datetime.serializers.FormattedLocalDateTimeSerializer
import kotlinx.serialization.Serializable

/**
 * Serializes [LocalDateTime] as "yyyy-MM-dd HH:mm:ss" instead of the default ISO 8601 format.
 */
private object LocalDateTimeSerializer : FormattedLocalDateTimeSerializer(
    name = "LocalDateTime",
    format = LocalDateTime.Format {
        date(LocalDate.Formats.ISO)
        char(' ')
        time(LocalTime.Formats.ISO)
    },
)

@Serializable
data class MyRemoteConfig(
    val myBoolean: Boolean,
    val myDouble: Double,
    val myLong: Long,
    val myString: String,
    @Serializable(with = LocalDateTimeSerializer::class) val myLocalDateTime: LocalDateTime,
)
