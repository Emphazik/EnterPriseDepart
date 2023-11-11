package com.example.enterprisedepart

import com.example.enterprisedepart.models.*
import javafx.collections.FXCollections
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.fxml.Initializable
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.control.cell.PropertyValueFactory
import javafx.stage.Stage
import javafx.util.Callback
import java.lang.Exception
import java.net.URL
import java.sql.SQLException
import java.time.LocalDate
import java.util.*
import java.util.logging.Level
import java.util.logging.Logger

class HelloController : Initializable {

    @FXML
    private lateinit var peopleTableView: TableView<PeopleModel>
    @FXML
    private lateinit var genderTableView: TableView<GenderModel>
    @FXML
    private lateinit var scienceTableView: TableView<ScienceModel>
    @FXML
    private lateinit var departmentTableView: TableView<DepartModel>
    @FXML
    private lateinit var ageGroupTableView: TableView<AgeGroup>
    @FXML
    private lateinit var periodTableView: TableView<Period>

    @FXML private lateinit var tabPane: TabPane

    @FXML private lateinit var addButton: Button

    private lateinit var db: DbContext

    private var depName = ""

    override fun initialize(url: URL?, rb: ResourceBundle?) {
        db = DbContext()
        refreshTable()
    }

    @FXML
    private fun onSaveClick() {
        val peoples = db.fetchPeople()
        val genders = db.fetchGenders()
        val sciences = db.fetchScience()
        val departs = db.fetchDepart()

        when (tabPane.selectionModel.selectedItem.id) {
            "peopleTab" -> {
                val all = peopleTableView.items
                all.remove(0, peoples.size)
                all.forEach(db::insertPeople)
            }
            "genderTab" -> {
                val all = genderTableView.items
                all.remove(0, genders.size)
                all.forEach(db::insertGender)
            }
            "scienceTab" -> {
                val all = scienceTableView.items
                all.remove(0, sciences.size)
                all.forEach(db::insertScience)
            }
            "departTab" -> {
                val all = departmentTableView.items
                all.remove(0, departs.size)
                all.forEach(db::insertDepartment)
            }
        }
        refreshTable()
    }

    @FXML
    private fun getAddView() {
        val peoples = db.fetchPeople()
        val genders = db.fetchGenders()
        val sciences = db.fetchScience()
        val departs = db.fetchDepart()

        when (tabPane.selectionModel.selectedItem.id) {
            "peopleTab" -> {
                peopleTableView.items.add(PeopleModel(
                    id = peoples.size + 1,
                    fio = "",
                    yo = LocalDate.now(),
                    address = "",
                    gender = genders.first.getId().toInt(),
                    science = sciences.first.getId().toInt(),
                    department = departs.first.getId().toInt(),
                    dateWork = LocalDate.now()
                ))
            }
            "genderTab" -> {
                genderTableView.items.add(GenderModel(genders.size + 1, ""))
            }
            "scienceTab" -> {
                scienceTableView.items.add(ScienceModel(sciences.size + 1, ""))
            }
            "departTab" -> {
                departmentTableView.items.add(DepartModel(departs.size + 1, ""))
            }
        }
    }

    @FXML
    private fun refreshTable() {
        try {
            initPeopleTable()
            initGenderTable()
            initScienceTable()
            initDepartTable()
            initAgeGroupTable()
            if (depName.isNotEmpty())
                initPeriodTable()
        } catch (ex: SQLException) {
            Logger.getLogger(HelloController::class.java.name).log(Level.SEVERE, null, ex)
        }
    }

    private fun initGenderTable() {
        val genders = db.fetchGenders()
        genderTableView.items = FXCollections.observableList(genders)
        genderTableView.columns[0].apply {
            cellValueFactory = PropertyValueFactory("Id")
            cellFactory = Callback { EditingCell<GenderModel>() }
            onEditCommit = EventHandler {
                it.tableView.items[it.tablePosition.row].setId(it.newValue.toString())
            }
        }
        genderTableView.columns[1].apply {
            cellValueFactory = PropertyValueFactory("Name")
            cellFactory = Callback { EditingCell<GenderModel>() }
            onEditCommit = EventHandler {
                it.tableView.items[it.tablePosition.row].setName(it.newValue.toString())
            }
        }
    }

    private fun initScienceTable() {
        val sciences = db.fetchScience()
        scienceTableView.items = FXCollections.observableList(sciences)
        scienceTableView.columns[0].apply {
            cellValueFactory = PropertyValueFactory("Id")
            cellFactory = Callback { EditingCell<ScienceModel>() }
            onEditCommit = EventHandler {
                it.tableView.items[it.tablePosition.row].setId(it.newValue.toString())
            }
        }
        scienceTableView.columns[1].apply {
            cellValueFactory = PropertyValueFactory("Type")
            cellFactory = Callback { EditingCell<ScienceModel>() }
            onEditCommit = EventHandler {
                it.tableView.items[it.tablePosition.row].setType(it.newValue.toString())
            }
        }
    }

    private fun initDepartTable() {
        val departs = db.fetchDepart()
        departmentTableView.items = FXCollections.observableList(departs)
        departmentTableView.columns[0].apply {
            cellValueFactory = PropertyValueFactory("Id")
            cellFactory = Callback { EditingCell<DepartModel>() }
            onEditCommit = EventHandler {
                it.tableView.items[it.tablePosition.row].setId(it.newValue.toString())
            }
        }
        departmentTableView.columns[1].apply {
            cellValueFactory = PropertyValueFactory("Name")
            cellFactory = Callback { EditingCell<DepartModel>() }
            onEditCommit = EventHandler {
                it.tableView.items[it.tablePosition.row].setName(it.newValue.toString())
            }
        }
    }

    private fun initPeopleTable() {
        val peoples = db.fetchPeople()
        val departs = db.fetchDepart()
        val sciences = db.fetchScience()
        val genders = db.fetchGenders()
        peopleTableView.items = FXCollections.observableList(peoples)
        peopleTableView.columns[0].apply {
            cellValueFactory = PropertyValueFactory("Id")
            cellFactory = Callback { EditingCell<PeopleModel>() }
            onEditCommit = EventHandler {
                it.tableView.items[it.tablePosition.row].setId(it.newValue.toString())
            }
        }
        peopleTableView.columns[1].apply {
            cellValueFactory = PropertyValueFactory("Fio")
            cellFactory = Callback { EditingCell<PeopleModel>() }
            onEditCommit = EventHandler {
                it.tableView.items[it.tablePosition.row].setFio(it.newValue.toString())
            }
        }
        peopleTableView.columns[2].apply {
            cellValueFactory = PropertyValueFactory("Yo")
            cellFactory = Callback { EditingCell<PeopleModel>() }
            onEditCommit = EventHandler {
                it.tableView.items[it.tablePosition.row].setYo(it.newValue.toString())
            }
        }
        peopleTableView.columns[3].apply {
            cellValueFactory = PropertyValueFactory("Address")
            cellFactory = Callback { EditingCell<PeopleModel>() }
            onEditCommit = EventHandler {
                it.tableView.items[it.tablePosition.row].setAddress(it.newValue.toString())
            }
        }
        peopleTableView.columns[4].apply {
            cellValueFactory = PropertyValueFactory("Gender")
            cellFactory = Callback { ComboBoxEditingCell<PeopleModel, GenderModel>(genders) }
            onEditCommit = EventHandler {
                it.tableView.items[it.tablePosition.row].setGender(it.newValue.toString().split(" ")[0])
            }
        }
        peopleTableView.columns[5].apply {
            cellValueFactory = PropertyValueFactory("Science")
            cellFactory = Callback { ComboBoxEditingCell<PeopleModel, ScienceModel>(sciences) }
            onEditCommit = EventHandler {
                it.tableView.items[it.tablePosition.row].setScience(it.newValue.toString().split(" ")[0])
            }
        }
        peopleTableView.columns[6].apply {
            cellValueFactory = PropertyValueFactory("Department")
            cellFactory = Callback { ComboBoxEditingCell<PeopleModel, DepartModel>(departs) }
            onEditCommit = EventHandler {
                it.tableView.items[it.tablePosition.row].setDepartment(it.newValue.toString().split(" ")[0])
            }
        }
        peopleTableView.columns[7].apply {
            cellValueFactory = PropertyValueFactory("DateWork")
            cellFactory = Callback { EditingCell<PeopleModel>() }
            onEditCommit = EventHandler {
                it.tableView.items[it.tablePosition.row].setDateWork(it.newValue.toString())
            }
        }
    }

    private fun initAgeGroupTable() {
        val ageGroups = db.queryOne()

        ageGroupTableView.items = FXCollections.observableList(ageGroups)
        ageGroupTableView.columns[0].apply {
            cellValueFactory = PropertyValueFactory("Name")
        }
        ageGroupTableView.columns[1].apply {
            cellValueFactory = PropertyValueFactory("Gender")
        }
        ageGroupTableView.columns[2].apply {
            cellValueFactory = PropertyValueFactory("Older")
        }
        ageGroupTableView.columns[3].apply {
            cellValueFactory = PropertyValueFactory("OlderPercent")
        }
        ageGroupTableView.columns[4].apply {
            cellValueFactory = PropertyValueFactory("Younger")
        }
        ageGroupTableView.columns[5].apply {
            cellValueFactory = PropertyValueFactory("YoungerPercent")
        }
    }

    private fun initPeriodTable() {
        val periods = db.queryTwo(depName)

        periodTableView.items = FXCollections.observableList(periods)
        periodTableView.columns[0].apply {
            cellValueFactory = PropertyValueFactory("Name")
        }
        periodTableView.columns[1].apply {
            cellValueFactory = PropertyValueFactory("Count")
        }
        periodTableView.columns[2].apply {
            cellValueFactory = PropertyValueFactory("StaffCount")
        }
        periodTableView.columns[3].apply {
            cellValueFactory = PropertyValueFactory("Percent")
        }
    }

    @FXML
    private fun onSwitchTab() {
        if (depName.isNotEmpty()) {
            depName = ""
            return
        }
        try {
            val loader = FXMLLoader(Resourses.get("add-view.fxml"))
            val stage = Stage()
            stage.scene = Scene(loader.load())
            val controller = loader.getController<AddController>()
            stage.isResizable = false
            stage.showAndWait()
            depName = controller.departName
        } catch (ex: Exception) {
            println(ex.message)
        }
        initPeriodTable()
    }

}
