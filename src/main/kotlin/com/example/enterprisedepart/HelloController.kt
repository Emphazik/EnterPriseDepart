package com.example.enterprisedepart

import com.example.enterprisedepart.models.PeopleModel
import javafx.collections.FXCollections
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.fxml.Initializable
import javafx.scene.Scene
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.control.cell.PropertyValueFactory
import javafx.scene.input.MouseEvent
import javafx.stage.Stage
import java.net.URL
import java.sql.SQLException
import java.time.LocalDate
import java.util.*
import java.util.logging.Level
import java.util.logging.Logger

class HelloController<Parent> : Initializable {

    @FXML
    private lateinit var tableView: TableView<PeopleModel>

    @FXML
    private lateinit var columnId: TableColumn<PeopleModel, Int>

    @FXML
    private lateinit var columnFio: TableColumn<PeopleModel, String>

    @FXML
    private lateinit var columnBd: TableColumn<PeopleModel, LocalDate>

    @FXML
    private lateinit var columnAddress: TableColumn<PeopleModel, String>

    @FXML
    private lateinit var columnGender: TableColumn<PeopleModel, Int>

    @FXML
    private lateinit var columnScience: TableColumn<PeopleModel, Int>

    @FXML
    private lateinit var columnDepartment: TableColumn<PeopleModel, Int>

    @FXML
    private lateinit var columnDateWork: TableColumn<PeopleModel, LocalDate>
    private var peopleList = FXCollections.emptyObservableList<PeopleModel>()


    override fun initialize(url: URL?, rb: ResourceBundle?) {
        // TODO
        loadDate()
    }

    @FXML
    private fun getAddView(event: MouseEvent) {
        try {
            val loader = FXMLLoader(Resourses.get("add-view.fxml"))
            val scene = Scene(loader.load())
            val stage = Stage()
            stage.scene = scene
            stage.show()
        } catch (ex: Exception) {
            Logger.getLogger(HelloController::class.java.getName()).log(Level.SEVERE, null, ex)
        }
    }

    @FXML
    private fun refreshTable() {
        try {
            val ctx = DbContext()
            peopleList = FXCollections.observableList(ctx.fetchPeople())
            tableView.setItems(peopleList)
        } catch (ex: SQLException) {
            Logger.getLogger(HelloController::class.java.name).log(Level.SEVERE, null, ex)
        }
    }

    @FXML
    private fun save() {

    }

    private fun loadDate() {
        refreshTable()

        columnId.cellValueFactory = PropertyValueFactory("id")
        columnFio.cellValueFactory = PropertyValueFactory("fio")
        columnBd.cellValueFactory = PropertyValueFactory("yo")
        columnAddress.cellValueFactory = PropertyValueFactory("address")
        columnGender.cellValueFactory = PropertyValueFactory("gender")
        columnScience.cellValueFactory = PropertyValueFactory("science")
        columnDepartment.cellValueFactory = PropertyValueFactory("department")
        columnDateWork.cellValueFactory = PropertyValueFactory("dateWork")
    }
}




