package org.beru.ide.beruide.styles

import javafx.scene.Node
import javafx.scene.image.Image
import org.kordamp.ikonli.javafx.FontIcon
import java.io.File

class Icons() {

    public fun getIconsByExtension(file: File) : Image{
        try{
            return Extensions.FILES_16px.valueOf(file.extension.uppercase()).image
        }catch(ex: IllegalArgumentException){
            if(file.isDirectory)
                return Extensions.FILES_16px.FOLDER.image

            return Extensions.FILES_16px.FILE.image
        }
    }
    public fun getIconsByExtension_512Px(file: File) : Image{
        try{
            return Extensions.FILES.valueOf(file.extension.uppercase()).image
        }catch(ex: IllegalArgumentException){
            if(file.isDirectory)
                return Extensions.FILES.FOLDER.image

            return Extensions.FILES.FILE.image
        }
    }

}