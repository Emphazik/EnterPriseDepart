package com.example.enterprisedepart.models

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty

class DepartModel(id: Int, name: String?) {
    private val id: SimpleStringProperty
    private val name: SimpleStringProperty

    init {
        this.id = SimpleStringProperty(id.toString())
        this.name = SimpleStringProperty(name)
    }

    fun getId(): String {
        return id.get()
    }

    fun idProperty(): SimpleStringProperty {
        return id
    }

    fun setId(id: String) {
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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DepartModel

        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }


}
