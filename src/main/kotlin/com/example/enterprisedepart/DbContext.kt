package com.example.enterprisedepart

import com.example.enterprisedepart.models.*
import com.example.enterprisedepart.tables.Department
import com.example.enterprisedepart.tables.Gender
import com.example.enterprisedepart.tables.People
import com.example.enterprisedepart.tables.Science
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.CaseWhenElse
import org.jetbrains.exposed.sql.CaseWhen
import org.jetbrains.exposed.sql.SqlExpressionBuilder.case
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.greater
import org.jetbrains.exposed.sql.SqlExpressionBuilder.lessEq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.minus
import org.jetbrains.exposed.sql.javatime.CurrentDate
import org.jetbrains.exposed.sql.javatime.year
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDate
import java.time.Year

class DbContext {
    private val db = Database.connect(
        "jdbc:postgresql://localhost:5432/people_department",
        user = "postgres", password = "root"
    )

    fun insertPeople(peopleModel: PeopleModel) = transaction(db) {
        People.insert {
            it[id] = peopleModel.getId().toInt()
            it[fio] = peopleModel.getFio()
            it[yo] = LocalDate.parse(peopleModel.getYo())
            it[address] = peopleModel.getAddress()
            it[gender] = peopleModel.getGender().toInt()
            it[science] = peopleModel.getScience().toInt()
            it[department] = peopleModel.getDepartment().toInt()
            it[dateWork] = LocalDate.parse(peopleModel.getDateWork())
        }
    }

    fun insertDepartment(departModel: DepartModel) = transaction(db) {
        Department.insert {
            it[id] = departModel.getId().toInt()
            it[departmentName] = departModel.getName()
        }
    }

    fun insertGender(genderModel: GenderModel) = transaction(db) {
        Gender.insert {
            it[id] = genderModel.getId().toInt()
            it[genderName] = genderModel.getName()
        }
    }

    fun insertScience(scienceModel: ScienceModel) = transaction(db) {
        Science.insert {
            it[id] = scienceModel.getId().toInt()
            it[scienceType] = scienceModel.getType()
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

    fun queryOne() = transaction(db) {
        (People innerJoin Department innerJoin Gender)
            .slice(
                Department.departmentName,
                Gender.genderName,
                case()
                    .When(People.yo.year() greater 35, intParam(1))
                    .Else(Op.nullOp()).count(),
                case()
                    .When(People.yo.year() lessEq 35, intParam(1))
                    .Else(Op.nullOp()).count()
            )
            .selectAll()
            .groupBy(Department.departmentName, Gender.genderName)
            .map {
                val departmentName = it[Department.departmentName]
                val gender = it[Gender.genderName]
                val olderThan35 = it[case()
                    .When(People.yo.year() greater 35, intParam(1))
                    .Else(Op.nullOp()).count()]
                val youngerThan35 = it[case()
                    .When(People.yo.year() lessEq 35, intParam(1))
                    .Else(Op.nullOp()).count()]
                val totalCount = olderThan35 + youngerThan35

                AgeGroup(
                    departmentName,
                    gender,
                    olderThan35.toInt(),
                    (olderThan35.toDouble() * 100 / totalCount),
                    youngerThan35.toInt(),
                    (youngerThan35.toDouble() * 100 / totalCount)
                )
            }
    }

    fun queryTwo(name: String) = transaction(db) {
        val tenYearsAgo = LocalDate.now().minusYears(10) // Текущая дата минус 10 лет
        val isTenOrMoreYears = People.dateWork lessEq tenYearsAgo
        (People innerJoin Department)
            .slice(Department.departmentName, People.fio.count(), case().When(isTenOrMoreYears, intParam(1)).Else(Op.nullOp()).count())
            .selectAll()
            .groupBy(Department.departmentName)
            .having { Department.departmentName eq name }
            .map { r ->
                val departmentName = r[Department.departmentName]
                val allCount = r[People.fio.count()]
                val count = r[case().When(isTenOrMoreYears, intParam(1)).Else(Op.nullOp()).count()]
                Period(departmentName, allCount.toInt(), count.toInt(), (count * 100.0 / allCount))
            }
    }

}