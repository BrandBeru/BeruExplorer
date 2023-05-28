package org.beru.ide.beruide.application.options

import javafx.application.Platform
import javafx.scene.control.ProgressBar
import org.beru.ide.beruide.application.Views
import java.io.File

class Search(text: String) {
    var text = text

    public fun file(file: File){
        if(file.isFile)
            return
        for(f in file.listFiles()){
            if(f.name.contains(text, true)) {
                Platform.runLater {
                    Views.fileExplorer.loadButton(f)
                    Views.fileExplorer.details()
                }
            }
            if (f.isDirectory)
                file(f)
        }
    }
}