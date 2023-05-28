package org.beru.ide.beruide.styles

import javafx.scene.control.Alert
import javafx.scene.control.ButtonType
import javafx.scene.control.ToggleButton
import javafx.scene.control.ToggleGroup

class Messages {
    public fun info(text: String){
        println(text)
    }
    public fun confirmation(title: String): Boolean{
        val alert = Alert(Alert.AlertType.CONFIRMATION,  title, ButtonType.YES, ButtonType.CANCEL)

        alert.showAndWait()

        return alert.result == ButtonType.YES
    }
}