package com.example.enterprisedepart

import com.example.enterprisedepart.models.DepartModel
import com.example.enterprisedepart.models.GenderModel
import com.example.enterprisedepart.models.PeopleModel
import com.example.enterprisedepart.models.ScienceModel
import com.example.enterprisedepart.tables.Department
import com.example.enterprisedepart.tables.Gender
import com.example.enterprisedepart.tables.People
import com.example.enterprisedepart.tables.Science
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class DbContext {
    private val db = Database.connect(
        "jdbc:postgresql://localhost:5432/people_department",
        driver = "org.postgresql.Driver",
        user = "postgres", password = "root"
    )

    fun fetchGenders(): List<GenderModel> = transaction(db) {
        Gender.selectAll().map {
            GenderModel(
                it[Gender.id].value,
                it[Gender.genderName]
            )
        }
    }

    fun fetchDepart(): List<DepartModel> = transaction(db) {
        Department.selectAll().map {
            DepartModel(
                it[Department.id].value,
                it[Department.departmentName]
            )
        }
    }

    fun fetchScience(): List<ScienceModel> = transaction(db) {
        Science.selectAll().map {
            ScienceModel(
                it[Science.id].value,
                it[Science.scienceType]
            )
        }
    }

    fun fetchPeople(): List<PeopleModel> = transaction(db) {
        val genders = fetchGenders()
        val sciences = fetchScience()
        val departments = fetchDepart()
        People.selectAll().map {
            PeopleModel(
                it[People.id].value,
                it[People.fio],
                it[People.yo],
                it[People.address],
                genders.first { g -> g.id == it[People.gender]},
                sciences.first { s -> s.id == it[People.science]},
                departments.first { d -> d.id == it[People.department]},
                it[People.dateWork]
            )
        }
    }
}