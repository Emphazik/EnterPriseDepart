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
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import java.sql.Date
import java.time.LocalDate

class DbContext {
    private val db = Database.connect(
        "jdbc:postgresql://localhost:5432/people_department",
        user = "postgres", password = "root"
    )
    fun insertPeople(peopleModel: PeopleModel) = transaction(db) {
        People.insert{
            it[id] = peopleModel.getId()
            it[fio] = peopleModel.getFio()
            it[yo] = LocalDate.parse(peopleModel.getYo())
            it[address] = peopleModel.getAddress()
            it[gender] = peopleModel.getGender()
            it[science] = peopleModel.getScience()
            it[department] = peopleModel.getDepartment()
            it[dateWork] = LocalDate.parse(peopleModel.getDateWork())
        }
    }

    fun insertDepartment(departModel: DepartModel) = transaction(db) {
        Department.insert{
            it[id] = departModel.getId()
            it[departmentName] = departModel.getName()
        }
    }

    fun insertGender(genderModel: GenderModel) = transaction(db){
        Gender.insert {
            it[id] = genderModel.getId()
            it[genderName] = genderModel.getName()
        }
    }

    fun insertScience(scienceModel: ScienceModel) = transaction(db){
        Science.insert{
            it[id] = scienceModel.getId()
            it[scienceType] = scienceModel.gettype()
        }
    }

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
        People.selectAll().map {
            PeopleModel(
                it[People.id].value,
                it[People.fio],
                it[People.yo],
                it[People.address],
                it[People.gender],
                it[People.science],
                it[People.department],
                it[People.dateWork]
            )
        }
    }
}