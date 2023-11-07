package com.example.enterprisedepart.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.date

object People: IntIdTable() {
    val fio = varchar("fio", 50)
    val yo = date(name = "yo")
    val address = varchar("address", 255)
    val gender = integer("gender_id").references(Gender.id)
    val science = integer("science_id").references(Science.id)
    val department = integer("department_id").references(Department.id)
    val dateWork = date(name = "date_work")
}