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

    fun getId(): Int {
        return id.get()
    }

    fun idProperty(): SimpleIntegerProperty {
        return id
    }

    fun setId(id: Int) {
        this.id.set(id)
    }

    fun gettype(): String {
        return type.get()
    }

    fun typeProperty(): SimpleStringProperty {
        return type
    }

    fun settype(type: String?) {
        this.type.set(type)
    }
    override fun toString(): String {
        return "${getId()} ${gettype()}"
    }
}
