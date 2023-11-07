package com.example.enterprisedepart.models

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import java.time.LocalDate
import java.util.*

class PeopleModel(
    id: Int,
    fio: String,
    yo: LocalDate,
    address: String,
    gender: Int,
    science: Int,
    department: Int,
    dateWork: LocalDate
) {
    private val id: SimpleIntegerProperty
    private val fio: SimpleStringProperty
    private val yo: SimpleStringProperty
    private val address: SimpleStringProperty
    private val gender: SimpleIntegerProperty
    private val science: SimpleIntegerProperty
    private val department: SimpleIntegerProperty
    private val dateWork: SimpleStringProperty

    init {
        this.id = SimpleIntegerProperty(id)
        this.fio = SimpleStringProperty(fio)
        this.yo = SimpleStringProperty(yo.toString())
        this.address = SimpleStringProperty(address)
        this.gender = SimpleIntegerProperty(gender)
        this.science = SimpleIntegerProperty(science)
        this.department = SimpleIntegerProperty(department)
        this.dateWork = SimpleStringProperty(dateWork.toString())
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

    fun getFio(): String {
        return fio.get()
    }

    fun fioProperty(): SimpleStringProperty {
        return fio
    }

    fun setFio(fio: String) {
        this.fio.set(fio)
    }

    fun getYo(): String {
        return yo.get()
    }

    fun yoProperty(): SimpleStringProperty {
        return yo
    }

    fun setYo(yo: String) {
        this.yo.set(yo)
    }

    fun getAddress(): String {
        return address.get()
    }

    fun addressProperty(): SimpleStringProperty {
        return address
    }

    fun setAddress(address: String) {
        this.address.set(address)
    }

    fun getGender(): Int {
        return gender.get()
    }

    fun genderProperty(): SimpleIntegerProperty {
        return gender
    }

    fun setGender(gender: Int) {
        this.gender.set(gender)
    }

    fun getScience(): Int {
        return science.get()
    }

    fun scienceProperty(): SimpleIntegerProperty {
        return science
    }

    fun setScience(science: Int) {
        this.science.set(science)
    }

    fun getDepartment(): Int {
        return department.get()
    }

    fun departmentProperty(): SimpleIntegerProperty {
        return department
    }

    fun setDepartment(department: Int) {
        this.department.set(department)
    }

    fun getDateWork(): String {
        return dateWork.get()
    }

    fun dateWorkProperty(): SimpleStringProperty {
        return dateWork
    }

    fun setDateWork(dateWork: String) {
        this.dateWork.set(dateWork)
    }
}
