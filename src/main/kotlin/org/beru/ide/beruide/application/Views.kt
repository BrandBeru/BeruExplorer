package org.beru.ide.beruide.application

import javafx.application.Application
import javafx.stage.Stage
import org.beru.ide.beruide.application.sectionsController.ExplorerController
import java.io.File

object Views {
    var stage: Stage = Stage()
    var app: AppController = AppController()
    var fileExplorer: ExplorerController = ExplorerController(File(""))
}