package com.example.enterprisedepart

import com.example.enterprisedepart.models.DepartModel
import javafx.collections.FXCollections
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Button
import javafx.scene.control.ChoiceBox
import javafx.stage.Stage
import java.net.URL
import java.util.*

class AddController: Initializable {

    @FXML private lateinit var choiceBox: ChoiceBox<DepartModel>
    @FXML private lateinit var showBtn: Button

    var departName = ""
    private val db = DbContext()

    override fun initialize(url: URL?, rb: ResourceBundle?) {
        choiceBox.items = FXCollections.observableList(db.fetchDepart())

    }

    @FXML private fun onShowClick() {
        departName = choiceBox.value.getName()
        (showBtn.scene.window as Stage).close()
    }

}
