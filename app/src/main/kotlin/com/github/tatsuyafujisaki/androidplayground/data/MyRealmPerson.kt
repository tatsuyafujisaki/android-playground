package com.github.tatsuyafujisaki.androidplayground.data

import io.realm.kotlin.internal.toDuration
import io.realm.kotlin.types.RealmInstant
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import kotlin.time.Duration
import org.mongodb.kbson.ObjectId

class MyRealmPerson : RealmObject {
    @PrimaryKey
    var id: ObjectId = ObjectId()
    var name: String = ""
    var age: Int = 0
    var createdAt: Duration = RealmInstant.now().toDuration()
}
