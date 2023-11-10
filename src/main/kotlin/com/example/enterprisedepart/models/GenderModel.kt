package com.example.enterprisedepart.models

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty

class GenderModel(id: Int, name: String?) {
    private val id: SimpleIntegerProperty
    private val name: SimpleStringProperty

    init {
        this.id = SimpleIntegerProperty(id)
        this.name = SimpleStringProperty(name)
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

    fun getName(): String {
        return name.get()
    }

    fun nameProperty(): SimpleStringProperty {
        return name
    }

    fun setName(name: String?) {
        this.name.set(name)
    }
    override fun toString(): String {
        return "${getId()} ${getName()}"
    }
}
