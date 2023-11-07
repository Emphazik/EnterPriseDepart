package com.example.enterprisedepart

import com.example.enterprisedepart.models.PeopleModel
import javafx.collections.FXCollections
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.control.cell.PropertyValueFactory
import javafx.scene.control.cell.TextFieldTableCell
import java.net.URL
import java.time.LocalDate
import java.util.*

class HelloController: Initializable {

    @FXML
    private lateinit var tableView: TableView<PeopleModel>

    @FXML private lateinit var columnId: TableColumn<PeopleModel, Int>
    @FXML private lateinit var columnFio: TableColumn<PeopleModel, String>
    @FXML private lateinit var columnBd: TableColumn<PeopleModel, LocalDate>
    @FXML private lateinit var columnAddress: TableColumn<PeopleModel, String>
    @FXML private lateinit var columnGender: TableColumn<PeopleModel, Int>
    @FXML private lateinit var columnScience: TableColumn<PeopleModel, Int>
    @FXML private lateinit var columnDepartment: TableColumn<PeopleModel, Int>
    @FXML private lateinit var columnDateWork: TableColumn<PeopleModel, LocalDate>


    override fun initialize(p0: URL?, p1: ResourceBundle?) {
        val ctx = DbContext()
        val peoples = ctx.fetchPeople()
        tableView.items = FXCollections.observableList(peoples)

        columnId.cellValueFactory = PropertyValueFactory("id")
        columnFio.cellValueFactory =  PropertyValueFactory("fio")
        columnBd.cellValueFactory =  PropertyValueFactory("yo")
        columnAddress.cellValueFactory =  PropertyValueFactory("address")
        columnGender.cellValueFactory =  PropertyValueFactory("gender_id")
        columnScience.cellValueFactory =  PropertyValueFactory("science_id")
        columnDepartment.cellValueFactory =  PropertyValueFactory("department_id")
        columnDateWork.cellValueFactory =  PropertyValueFactory("date_work")

//        columnId.cellFactory = TextFieldTableCell.forTableColumn()
//        columnFio.cellFactory = TextFieldTableCell.forTableColumn()
//        columnBd.cellFactory = TextFieldTableCell.forTableColumn()
//        columnAddress.cellFactory = TextFieldTableCell.forTableColumn()
//        columnGender.cellFactory = TextFieldTableCell.forTableColumn()
//        columnScience.cellFactory = TextFieldTableCell.forTableColumn()
//        columnDepartment.cellFactory = TextFieldTableCell.forTableColumn()
//        columnDateWork.cellFactory = TextFieldTableCell.forTableColumn()
    }
}