package org.beru.ide.beruide.files

import javafx.fxml.FXMLLoader
import javafx.scene.layout.AnchorPane
import org.beru.ide.beruide.HelloApplication
import org.beru.ide.beruide.application.sectionsController.CodeController
import org.beru.ide.beruide.application.sectionsController.ExplorerController
import org.beru.ide.beruide.application.sectionsController.ImageController
import org.beru.ide.beruide.styles.Extensions
import org.beru.ide.beruide.styles.Types
import java.io.File

class OpenFile {

    public fun byExtension(file: File): AnchorPane{
        try {
            if(!file.isDirectory)
            {
                when(Extensions.FILES_16px.valueOf(file.extension.uppercase()).type){
                    Types.CODE -> return openCode(file)
                    Types.IMAGE -> return openImage(file)
                    else -> return openError()
                }
            }else{
                return fileExplorer(file)
            }
        }catch (ex: IllegalAccessException){
            return openError()
        }catch (ex: IllegalArgumentException) {
            return openError()
        }
    }
    public fun getName(file: File): String{
        if(!file.isDirectory)
        {
            when(Extensions.FILES_16px.valueOf(file.extension.uppercase()).type){
                Types.CODE -> return "Code Editor"
                Types.IMAGE -> return "Image Editor"
                else -> return "Error"
            }
        }else{
            return "File Explorer"
        }
    }
    private fun openCode(file: File): AnchorPane {
        val fxml: FXMLLoader = FXMLLoader(HelloApplication::class.java.getResource("TextEditor.fxml"))
        val code = CodeController(file)
        fxml.setController(code)

        return fxml.load() as AnchorPane
    }
    private fun openImage(file: File): AnchorPane{
        val fxml: FXMLLoader = FXMLLoader(HelloApplication::class.java.getResource("ImageEditor.fxml"))
        val image = ImageController(file)
        fxml.setController(image)

        return fxml.load() as AnchorPane
    }
    private fun fileExplorer(file: File): AnchorPane{
        val fxml: FXMLLoader = FXMLLoader(HelloApplication::class.java.getResource("FileExplorer.fxml"))
        val explorer = ExplorerController(file)
        fxml.setController(explorer)

        return fxml.load() as AnchorPane
    }
    private fun openError(): AnchorPane{
        val fxml: FXMLLoader = FXMLLoader(HelloApplication::class.java.getResource("ErrorPane.fxml"))

        return fxml.load()
    }
}