package com.example.enterprisedepart
import com.example.enterprisedepart.DbContext
import com.example.enterprisedepart.models.DepartModel
import com.example.enterprisedepart.models.GenderModel
import com.example.enterprisedepart.models.PeopleModel
import com.example.enterprisedepart.models.ScienceModel
import javafx.collections.FXCollections
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Alert
import javafx.scene.control.ChoiceBox
import javafx.scene.control.DatePicker
import javafx.scene.control.TextField
import javafx.scene.input.MouseEvent
import java.net.URL

import java.time.LocalDate
import java.util.*


class AddController: Initializable {
    @FXML private lateinit var fioField: TextField
    @FXML private lateinit var yoField: DatePicker
    @FXML private lateinit var addressField: TextField
    @FXML private lateinit var genderField: ChoiceBox<GenderModel>
    @FXML private lateinit var scienceField: ChoiceBox<ScienceModel>
    @FXML private lateinit var departField: ChoiceBox<DepartModel>
    @FXML private lateinit var dateWorkField: DatePicker

    private var update = false
    private val db = DbContext()

    override fun initialize(url: URL?, rb: ResourceBundle?) {
        // TODO
        genderField.items = FXCollections.observableList(db.fetchGenders())
        scienceField.items = FXCollections.observableList(db.fetchScience())
        departField.items = FXCollections.observableList(db.fetchDepart())

    }
    @FXML
    private fun addData(event: MouseEvent) {
        val id = db.fetchPeople().size + 1
        val fio = fioField.text
        val yo = yoField.value
        val address = addressField.text
        val gender = genderField.value.getId()
        val science = scienceField.value.getId()
        val depart = departField.value.getId()
        val dateWork = dateWorkField.value

        if (fio.isEmpty() || yo == null || address.isEmpty() || dateWork == null) {
            val alert = Alert(Alert.AlertType.ERROR)
            alert.headerText = null
            alert.contentText = "Please Fill All DATA"
            alert.showAndWait()
        } else {
            db.insertPeople(PeopleModel(id, fio, yo, address, gender, science, depart, dateWork))
            cleanData()
        }
    }

    @FXML
    private fun cleanData() {
        fioField.clear()
        yoField.value = null
        addressField.clear()
        dateWorkField.value = null
    }

    fun setTextField(id: Int, fio: String, yo: LocalDate, address: String, gender: String, science: String, depart: String, dateWork: LocalDate) {
        fioField.text = fio
        yoField.value = yo
        addressField.text = address
        dateWorkField.value = dateWork
    }

    fun setUpdate(b: Boolean) {
        update = b
    }
}
