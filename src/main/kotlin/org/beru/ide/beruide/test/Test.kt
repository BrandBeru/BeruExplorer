package org.beru.ide.beruide.test

import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.TextArea
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.FontPosture
import javafx.scene.text.Text
import javafx.scene.text.TextFlow
import java.net.URL
import java.util.*

class Test : Initializable{
    @FXML lateinit var flow: TextArea

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        val text = Text("Testing this shit")
        text.fill = Color.BLACK
        text.setFont(Font.font("Helvetica", FontPosture.ITALIC, 40.0));
        flow.appendText("Testing this shit")
    }

}