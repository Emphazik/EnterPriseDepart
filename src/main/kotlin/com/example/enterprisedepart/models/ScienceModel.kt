package com.example.enterprisedepart.models

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty

class ScienceModel(id: Int, type: String?) {
    private val id: SimpleIntegerProperty
    private val type: SimpleStringProperty

    init {
        this.id = SimpleIntegerProperty(id)
        this.type = SimpleStringProperty(type)
    }

    fun getId(): String {
        return id.get().toString()
    }

    fun idProperty(): SimpleIntegerProperty {
        return id
    }

    fun setId(id: String) {
        this.id.set(id.toInt())
    }

    fun getType(): String {
        return type.get()
    }

    fun typeProperty(): SimpleStringProperty {
        return type
    }

    fun setType(type: String?) {
        this.type.set(type)
    }
    override fun toString(): String {
        return "${getId()} ${getType()}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ScienceModel

        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

}
