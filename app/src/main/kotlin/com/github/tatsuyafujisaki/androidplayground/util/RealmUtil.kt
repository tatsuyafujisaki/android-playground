package com.github.tatsuyafujisaki.androidplayground.util

import io.realm.kotlin.types.RealmInstant
import java.time.LocalDateTime
import java.time.ZoneOffset

object RealmUtil {
    fun convertToLocalDateTime(realmInstant: RealmInstant): LocalDateTime =
        LocalDateTime.ofEpochSecond(
            realmInstant.epochSeconds,
            realmInstant.nanosecondsOfSecond,
            ZoneOffset.UTC,
        )

    fun convertToRealmInstance(localDateTime: LocalDateTime) = RealmInstant.from(
        localDateTime.toEpochSecond(ZoneOffset.UTC),
        localDateTime.nano,
    )
}
