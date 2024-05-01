package com.github.tatsuyafujisaki.androidplayground.data

import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.types.RealmInstant
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class MyRealmPerson : RealmObject {
    @PrimaryKey
    var id: ObjectId = ObjectId()
    var name: String = ""
    var age: Int = 0
    var createdAt: RealmInstant = RealmInstant.now()
}

private fun main() {
    val realm = Realm.open(
        configuration = RealmConfiguration.create(
            schema = setOf(MyRealmPerson::class)
        )
    )
}
