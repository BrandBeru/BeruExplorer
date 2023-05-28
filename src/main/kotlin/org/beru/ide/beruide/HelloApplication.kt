package org.beru.ide.beruide

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import jfxtras.styles.jmetro.JMetro
import jfxtras.styles.jmetro.Style
import org.beru.ide.beruide.application.AppController
import org.beru.ide.beruide.application.Views

class HelloApplication : Application() {
    override fun start(stage: Stage) {
        //Application/Application.fxml
        val fxmlLoader = FXMLLoader(HelloApplication::class.java.getResource("Application/Application.fxml"))
        val con = AppController(parameters)
        fxmlLoader.setController(con)

        Views.stage = stage

        val root: Parent = fxmlLoader.load()

        val scene = Scene(root, 1366.0, 768.0)
        val metro = JMetro(scene.root, Style.DARK)
        stage.title = "BERU IDE"
        stage.scene = scene
        stage.show()
    }
}

fun main(args: Array<String>) {
    Application.launch(HelloApplication::class.java)
}