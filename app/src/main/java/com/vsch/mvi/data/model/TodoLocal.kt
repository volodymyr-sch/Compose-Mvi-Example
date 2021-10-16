package com.vsch.mvi.data.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class TodoLocal : RealmObject() {

    @PrimaryKey
    lateinit var id: String

    var text: String = ""
    var isChecked: Boolean = false

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TodoLocal

        if (id != other.id) return false
        if (text != other.text) return false
        if (isChecked != other.isChecked) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + text.hashCode()
        result = 31 * result + isChecked.hashCode()
        return result
    }
}