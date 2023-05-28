package org.beru.ide.beruide.application.sectionsController

import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Label
import javafx.scene.control.TextArea
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.net.URL
import java.util.*

class CodeController(file: File) : Initializable{
    @FXML
    lateinit var codificationText: Label
    @FXML
    lateinit var cursorPosText: Label
    @FXML
    lateinit var languageText: Label
    @FXML
    lateinit var codeText: TextArea

    private var file: File

    init {
        this.file = file
    }

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        val r = BufferedReader(FileReader(file))
        while (r.read() > 0)
            codeText.appendText(r.readText())
    }
}