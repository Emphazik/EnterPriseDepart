package com.example.enterprisedepart.tables

import org.jetbrains.exposed.dao.id.IntIdTable

object Gender: IntIdTable() {
    val genderName = char("gender_name", 1)
}