package org.beru.ide.beruide.application

import io.github.palexdev.materialfx.controls.MFXButton
import javafx.application.Application.Parameters
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Button
import javafx.scene.control.ContextMenu
import javafx.scene.control.MenuItem
import javafx.scene.control.RadioButton
import javafx.scene.control.SplitMenuButton
import javafx.scene.control.Tab
import javafx.scene.control.TabPane
import javafx.scene.control.ToggleButton
import javafx.scene.control.ToggleGroup
import javafx.scene.control.TreeItem
import javafx.scene.control.TreeView
import javafx.scene.image.ImageView
import javafx.scene.input.MouseEvent
import javafx.scene.layout.VBox
import javafx.stage.Popup
import org.beru.ide.beruide.files.FilesTree
import org.beru.ide.beruide.files.OpenFile
import org.beru.ide.beruide.styles.Icons
import java.io.File
import java.net.URL
import java.util.*
import kotlin.collections.HashMap

class AppController : Initializable{

    constructor(params: Parameters){
        path = params.raw.get(0)
    }
    constructor()

    @FXML lateinit var hiddenButton: MFXButton
    @FXML lateinit var projectsSplit: SplitMenuButton
    @FXML lateinit var textFiles: TabPane
    @FXML lateinit var treeFiles: TreeView<Any>

    private var path: String = "/home/beru"

    init {
        Views.app = this
    }
    @FXML
    override fun initialize(location: URL?, resources: ResourceBundle?) {
        loadItems()
        workspacesPopup()
    }
    fun loadItems(){
        projectsSplit.items.clear()
        val file = File(path)
        val tree: FilesTree = FilesTree(file)
        projectsSplit.text = file.name
        projectsSplit.items.add(MenuItem("SSH"))
        projectsSplit.items.addAll()
        treeFiles.root = TreeItem(file.name)
        tree.load(treeFiles.root, file)
    }
    @FXML
    fun itemSelected(file: File, tree: FilesTree, item: TreeItem<Any>){
        tree.load(item, file)
    }

    fun refreshFilesTree(actionEvent: ActionEvent) {
        loadItems()
    }

    fun addWorkSPace(actionEvent: ActionEvent) {
        popup.show(Views.stage)
    }
    var popup: Popup = Popup()
    var group = ToggleGroup()
    private fun workspacesPopup(){
        val pane = VBox()
        val ssh = RadioButton("SSH Connection")
        ssh.toggleGroup = group
        val ftp = RadioButton("FTP Connection")
        ftp.toggleGroup = group
        val bd = RadioButton("BD Manager")
        bd.toggleGroup = group
        val btn = Button("Open")
        pane.children.addAll(ssh, ftp, bd, btn)
        popup.content.addAll(pane)

        btn.setOnAction {
            val selected: RadioButton = group.selectedToggle as RadioButton
            println(selected.text)
            popup.hide()
        }
    }

    fun fileSelected(mouseEvent: MouseEvent) {
        val item = treeFiles.selectionModel.selectedItem
        if(item == null)
            return

        val file = File(fullPath(item))

        treeFiles.contextMenu = contextMenu(file)

        loadFile(file)
    }
    private var openTabs= mutableMapOf<String, Tab>()
    public fun loadFile(file: File){
        val name = OpenFile().getName(file)
        if(openTabs.containsKey(name)){
            val tab = openTabs.get(name)
            textFiles.selectionModel.select(tab)
            tab?.graphic = ImageView(Icons().getIconsByExtension(file))
            tab?.content = OpenFile().byExtension(file)
            tab?.text = file.name
        }else{
            val tab = Tab(file.name)
            tab.graphic = ImageView(Icons().getIconsByExtension(file))
            tab.content = OpenFile().byExtension(file)
            openTabs.put(name, tab)
            textFiles.tabs.add(tab)

            tab.setOnCloseRequest {
                openTabs.remove(name)
            }
        }
    }

    private fun fullPath(item: TreeItem<Any>): String{
        val sb = StringBuilder(path)
        buildPath(item, sb)
        return sb.toString()
    }
    fun buildPath(item: TreeItem<Any>, builder: StringBuilder){
        if(item.parent != null){
            buildPath(item.parent, builder);
            builder.append("/")
            builder.append(item.value)
        }
    }
    private fun contextMenu(file: File): ContextMenu{
        val new = MenuItem("New")
        val open = MenuItem("Open")
        val del = MenuItem("Delete")
        val copy = MenuItem("Copy")
        return ContextMenu(new, open,del, copy)
    }

}