package org.beru.ide.beruide.application.sectionsController

import editables.EditableToggleButton
import io.github.palexdev.materialfx.controls.MFXTextField
import javafx.application.Platform
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.*
import javafx.scene.image.ImageView
import javafx.scene.input.MouseButton
import javafx.scene.layout.FlowPane
import org.beru.ide.beruide.application.Views
import org.beru.ide.beruide.application.options.Search
import org.beru.ide.beruide.styles.Extensions
import org.beru.ide.beruide.styles.Icons
import org.beru.ide.beruide.styles.Messages
import java.io.File
import java.net.URL
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class ExplorerController(file: File): Initializable {
    private var file: File
    private var pathsHistory=  ArrayList<File>()

    @FXML
    lateinit var filesPane: FlowPane
    @FXML
    lateinit var pathText: Label
    @FXML
    lateinit var searchText: TextField
    @FXML
    lateinit var progressBar: ProgressBar
    lateinit var itemsText: Label
    lateinit var spaceText: Label

    private var pos: Int = 0

    init{
        this.file = file
        Views.fileExplorer = this
    }
    override fun initialize(location: URL?, resources: ResourceBundle?) {
        load(file)

        contextMenuClose(file)

        filesPane.setOnMouseClicked {
            if(it.button == MouseButton.SECONDARY)
                globalContextMenu.show(it.pickResult.intersectedNode, it.screenX, it.screenY)
            else
                globalContextMenu.hide()
        }
    }
    public fun load(f: File){
        filesPane.children.clear()
        pathText.text = f.absolutePath
        pathsHistory.add(f)

        loadButtonWithArray(f.listFiles())
    }
    public fun loadButtonWithArray(list: Array<File>){
        var files = LinkedList<ToggleButton>()
        val group = ToggleGroup()
        for(fi: File in list){
            var image: ImageView = ImageView(Icons().getIconsByExtension_512Px(fi))
            image.fitWidth = 32.0
            image.fitHeight = 32.0
            val button: ToggleButton = ToggleButton(fi.name, image)
            button.toggleGroup = group
            button.prefWidth = 64.0
            button.prefHeight = Button.USE_COMPUTED_SIZE
            button.isWrapText = true
            button.contentDisplay = ContentDisplay.TOP


            if(fi.isDirectory)
                files.addFirst(button)
            else
                files.add(button)
            button.setOnMouseClicked {
                if(it.button == MouseButton.PRIMARY) {

                    if (it.clickCount == 2) {
                        open(fi, fi.isDirectory)
                    }
                }
                if(fi.isDirectory)
                    button.contextMenu = contextMenuFolder(fi)

                else
                    button.contextMenu = contextMenuFile(fi)
            }
        }
        filesPane.children.addAll(files)
        details()
    }
    private val fm = NumberFormat.getInstance()
    public fun details(){
        itemsText.text = "Items: "+filesPane.children.size
        spaceText.text = "Free Space: " + fm.format(File(pathText.text).freeSpace.toDouble()/(Math.pow(1024.0, 3.0))) + " GB"
    }
    private val group = ToggleGroup()
    public fun loadButton(fi: File){
        val image: ImageView = ImageView(Icons().getIconsByExtension_512Px(fi))
        image.fitWidth = 32.0
        image.fitHeight = 32.0
        val button: ToggleButton = ToggleButton(fi.name, image)
        button.toggleGroup = group
        button.prefWidth = 64.0
        button.prefHeight = Button.USE_COMPUTED_SIZE
        button.isWrapText = true
        button.contentDisplay = ContentDisplay.TOP


        button.setOnMouseClicked {
            if(it.button == MouseButton.PRIMARY) {

                if (it.clickCount == 2) {
                    open(fi, fi.isDirectory)
                }
            }
            if(fi.isDirectory)
                button.contextMenu = contextMenuFolder(fi)

            else
                button.contextMenu = contextMenuFile(fi)
        }
        filesPane.children.add(button)
    }
    val globalContextMenu = ContextMenu()
    fun contextMenuClose(file: File){
        val folder = MenuItem("Create New Folder")
        folder.setOnAction {
            createFolder(file)
        }
        val file = MenuItem("Create new File")
        file.setOnAction {

        }
        val terminal = MenuItem("Open In Terminal")
        val arrange = Menu("Arrange Items")
        val groupArrange = ToggleGroup()
        val byName = RadioMenuItem("By Name")
        byName.toggleGroup = groupArrange
        val bySize = RadioMenuItem("By Size")
        bySize.toggleGroup = groupArrange
        val byType = RadioMenuItem("By Type")
        byType.toggleGroup = groupArrange
        arrange.items.add(byName)
        arrange.items.add(bySize)
        arrange.items.add(byType)
        globalContextMenu.items.add(folder)
        globalContextMenu.items.add(file)
        globalContextMenu.items.add(terminal)
        globalContextMenu.items.add(arrange)
    }
    fun contextMenuFolder(file: File): ContextMenu{
        val open = MenuItem("Open")
        open.setOnAction {
            open(file, true)
        }
        val openTab = MenuItem("Open in New Tab")
        openTab.setOnAction {
            openTab(file)
        }
        val copy = MenuItem("Copy")
        val rename = MenuItem("Rename")
        val delete = MenuItem("Delete")
        delete.setOnAction {
            delete(file)
        }
        val properties = MenuItem("Properties")
        return ContextMenu(open, openTab,rename, copy, delete, properties)
    }
    fun contextMenuFile(file: File): ContextMenu{
        val open = MenuItem("Open")
        open.setOnAction {
            open(file, false)
        }
        val copy = MenuItem("Copy")
        val rename = MenuItem("Rename")
        val del = MenuItem("Delete")
        del.setOnAction {
            delete(file)
        }
        val properties = MenuItem("Properties")
        return ContextMenu(open, copy,rename, del, properties)
    }
    fun open(file: File, isDirectory: Boolean){
        if(isDirectory) {
            load(file)
            pos++
        }
        else
            Views.app.loadFile(file)
    }
    fun openTab(file: File){

    }
    fun createFolder(file: File){
        val button = EditableToggleButton("New Folder", file);
        filesPane.children.add(button)
    }
    fun createFile(file: File){

    }
    fun delete(file: File){
        if(Messages().confirmation("Do you want delete this file?")){
            if(file.delete()){
                load(file.parentFile)
            }
        }
    }
    fun back(actionEvent: ActionEvent) {
        if(pos <= 0)
            pos = 0
        else
            pos--
        load(pathsHistory.get(pos))
    }
    private var searchThread: Thread = Thread()
    fun searchFiles(actionEvent: ActionEvent) {
        if(searchText.text.length < 3) {
            searchThread.stop()
            load(File(pathText.text))
            Messages().info("Min length to search 3 chars!")
        }
        else {
            filesPane.children.clear()
            progressBar.progress = ProgressBar.INDETERMINATE_PROGRESS
            val search = Search(searchText.text)
            searchThread = Thread(Runnable {
                search.file(File(pathText.text.toString()))
                Platform.runLater {
                    progressBar.progress = 0.0
                }
            })
            searchThread.isDaemon = true
            searchThread.start()
        }
    }
    fun forward(actionEvent: ActionEvent) {
        if(pos >= pathsHistory.size-1)
            pos = pathsHistory.size-1
        else
            pos++
        load(pathsHistory.get(pos))
    }

}