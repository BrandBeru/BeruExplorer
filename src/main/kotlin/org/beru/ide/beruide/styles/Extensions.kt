package org.beru.ide.beruide.styles

import javafx.scene.image.Image
import org.beru.ide.beruide.HelloApplication

class Extensions{
    enum class FILES(val image: Image, val type: Types){
        PNG(Image(HelloApplication::class.java.getResourceAsStream("Icons/512p/png.png")), Types.IMAGE),
        JPG(Image(HelloApplication::class.java.getResourceAsStream("Icons/512p/jpg.png")), Types.IMAGE),
        JPEG(Image(HelloApplication::class.java.getResourceAsStream("Icons/512p/jpeg.png")), Types.IMAGE),
        GIF(Image(HelloApplication::class.java.getResourceAsStream("Icons/512p/gif.png")), Types.IMAGE),
        WEBP(Image(HelloApplication::class.java.getResourceAsStream("Icons/512p/webp.png")), Types.IMAGE),
        PHP(Image(HelloApplication::class.java.getResourceAsStream("Icons/512p/php.png")), Types.CODE),
        JAVA(Image(HelloApplication::class.java.getResourceAsStream("Icons/512p/java.png")), Types.CODE),
        JS(Image(HelloApplication::class.java.getResourceAsStream("Icons/512p/js.png")), Types.CODE),
        KT(Image(HelloApplication::class.java.getResourceAsStream("Icons/512p/kotlin.png")), Types.CODE),
        CPP(Image(HelloApplication::class.java.getResourceAsStream("Icons/512p/cpp.png")), Types.CODE),
        C(Image(HelloApplication::class.java.getResourceAsStream("Icons/512p/c.png")), Types.CODE),
        CS(Image(HelloApplication::class.java.getResourceAsStream("Icons/512p/c-sharp.png")), Types.CODE),
        PY(Image(HelloApplication::class.java.getResourceAsStream("Icons/512p/py.png")), Types.CODE),

        FILE(Image(HelloApplication::class.java.getResourceAsStream("Icons/512p/files.png")), Types.CODE),
        FOLDER(Image(HelloApplication::class.java.getResourceAsStream("Icons/512p/folder.png")), Types.CODE)
    }
    enum class FILES_16px(val image: Image, val type: Types){
        PNG(Image(HelloApplication::class.java.getResourceAsStream("Icons/16p/image.png")), Types.IMAGE),
        JPG(Image(HelloApplication::class.java.getResourceAsStream("Icons/16p/image.png")), Types.IMAGE),
        JPEG(Image(HelloApplication::class.java.getResourceAsStream("Icons/16p/image.png")), Types.IMAGE),
        GIF(Image(HelloApplication::class.java.getResourceAsStream("Icons/16p/image.png")), Types.IMAGE),
        WEBP(Image(HelloApplication::class.java.getResourceAsStream("Icons/16p/image.png")), Types.IMAGE),
        PHP(Image(HelloApplication::class.java.getResourceAsStream("Icons/16p/php.png")), Types.CODE),
        JAVA(Image(HelloApplication::class.java.getResourceAsStream("Icons/16p/java.png")), Types.CODE),
        JS(Image(HelloApplication::class.java.getResourceAsStream("Icons/16p/js.png")), Types.CODE),
        KT(Image(HelloApplication::class.java.getResourceAsStream("Icons/16p/kotlin.png")), Types.CODE),
        CPP(Image(HelloApplication::class.java.getResourceAsStream("Icons/16p/cpp.png")), Types.CODE),
        C(Image(HelloApplication::class.java.getResourceAsStream("Icons/16p/c.png")), Types.CODE),
        CS(Image(HelloApplication::class.java.getResourceAsStream("Icons/16p/c-sharp.png")), Types.CODE),
        PY(Image(HelloApplication::class.java.getResourceAsStream("Icons/16p/py.png")), Types.CODE),

        FILE(Image(HelloApplication::class.java.getResourceAsStream("Icons/16p/file.png")), Types.CODE),
        FOLDER(Image(HelloApplication::class.java.getResourceAsStream("Icons/16p/folder.png")), Types.NULL)
    }
    enum class DEFAULT(val image: Image){

    }
}