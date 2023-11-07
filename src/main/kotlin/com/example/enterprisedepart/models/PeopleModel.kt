package com.example.enterprisedepart.models

import java.time.LocalDate
import java.util.*

data class PeopleModel (
    val id: Int,
    val fio: String,
    val yo: LocalDate,
    val address: String,
    val gender: GenderModel,
    val science: ScienceModel,
    val department: DepartModel,
    val dateWork: LocalDate
)
