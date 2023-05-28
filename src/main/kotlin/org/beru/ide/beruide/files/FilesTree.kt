package org.beru.ide.beruide.files

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.ContextMenu
import javafx.scene.control.Tab
import javafx.scene.control.TreeItem
import javafx.scene.control.TreeItem.TreeModificationEvent
import javafx.scene.image.ImageView
import javafx.scene.input.MouseButton
import javafx.scene.input.MouseEvent
import org.beru.ide.beruide.application.Views
import org.beru.ide.beruide.styles.Icons
import java.io.File
import java.util.Deque
import java.util.LinkedList

class FilesTree(val files : File) {
    private val rootItem = TreeItem<Any>(files.name);

    init {
        rootItem.isExpanded = true;
    }
    var child : TreeItem<Any>? = null
    public fun load(selected: TreeItem<Any>, path: File)
    {
        val elements: Deque<TreeItem<Any>> = LinkedList<TreeItem<Any>>()
        selected.children.clear()
        path.listFiles()?.forEach {
            val img:ImageView = ImageView(Icons().getIconsByExtension(it))
            img.fitWidth = 16.0
            img.fitHeight = 16.0

            val item = TreeItem<Any>(it.name, img)

            if(it.isDirectory)
                elements.addFirst(item)
            else {
                elements.add(item)
            }
            if (it.isDirectory && it.listFiles().size > 0) {
                item.children.add(TreeItem())
            }

            item.expandedProperty().addListener { observable, oldValue, newValue ->
                load(item, File(it.absolutePath));
            }
        }
        selected.children.addAll(elements)
    }
}