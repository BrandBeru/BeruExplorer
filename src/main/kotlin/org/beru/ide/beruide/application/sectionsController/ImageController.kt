package org.beru.ide.beruide.application.sectionsController

import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.image.Image
import javafx.scene.input.*
import javafx.scene.image.ImageView
import javafx.scene.input.MouseEvent
import javafx.scene.input.ScrollEvent
import java.io.File
import java.io.FileInputStream
import java.net.URL
import java.util.*

class ImageController(file: File) : Initializable{
    @FXML
    lateinit var image: ImageView
    private var file: File

    init {
        this.file = file
    }

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        val img = Image(FileInputStream(file))
        image.image = img
        image.fitWidth = img.width
        image.fitHeight = img.height

        image.setOnScroll {
            scrollStart(it)
        }
        image.setOnDragDetected {
            sendCopy(it)
        }
    }
    fun scrollStart(scrollEvent: ScrollEvent) {
        if(scrollEvent.isControlDown) {
            var zoom: Double = 1.05
            val deltY: Double = scrollEvent.deltaY
            if(deltY<0)
                zoom = 1.8 - zoom;
            image.scaleX *= zoom
            image.scaleY *= zoom
        }
    }
    fun sendCopy(event: MouseEvent){
        val db = image.startDragAndDrop(TransferMode.MOVE)
        val content = ClipboardContent()
        content.putString(file.name)
        content.putImage(image.image)
        db.setContent(content)
        event.consume()
    }

}