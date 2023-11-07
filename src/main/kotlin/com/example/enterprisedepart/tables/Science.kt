package com.example.enterprisedepart.tables

import org.jetbrains.exposed.dao.id.IntIdTable


object Science: IntIdTable() {
    val scienceType = varchar("science_type", 80)

}