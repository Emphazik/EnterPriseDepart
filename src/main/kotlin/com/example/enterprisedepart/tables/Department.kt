package com.example.enterprisedepart.tables

import org.jetbrains.exposed.dao.id.IntIdTable

object Department: IntIdTable() {
    val departmentName = varchar("department_name", 80)

}