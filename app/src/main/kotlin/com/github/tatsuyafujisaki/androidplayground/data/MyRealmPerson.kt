package com.github.tatsuyafujisaki.androidplayground.data

import io.realm.kotlin.types.RealmInstant
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class MyRealmPerson : RealmObject {
    @PrimaryKey
    var name: String = ""
    var age: Int = 0
    var createdAt: RealmInstant = RealmInstant.now()
}
