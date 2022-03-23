import javafx.beans.property.SimpleIntegerProperty
import javafx.geometry.Pos
import javafx.scene.Node
import javafx.scene.layout.VBox
import javafx.scene.text.FontWeight
import javafx.stage.FileChooser
import tornadofx.*

enum class ErrorMessages(val message: String) {
    FILENOTCHOSEN("You did not choose a file."),
    COULDNOTSAVE("The file could not be saved");

    override fun toString() = message
}

class TournamentView : View("Tournament App") {
    private val controller: TournamentController by inject()
    // This crap on the end is here because it's shallow copied without it, utter insanity
    private var currControllerPeopleList = controller.peopleService.list.map { it.copy() }
    private var round = SimpleIntegerProperty(1)

    private lateinit var matchesPane: VBox

    private fun renderMatchesInto(node: Node) {
        var count = 1

        node.add(label("Round ${round.value}").addClass(Styles.title2))

        if (controller.matches.isEmpty()) {
            node.add(label("No more matches!").addClass(Styles.title3).apply {
                style { fontWeight = FontWeight.NORMAL }
            })
            runCatching { node.add(label("${controller.peopleService.getWinner()} is the winner!").addClass(Styles.title3).apply {
                style { fontWeight = FontWeight.NORMAL }
            }) }
        } else {
            for (match in controller.matches) {

                node.add(label("Match $count").addClass(Styles.title3))

                listOf(match.person1, match.person2).asObservable().forEach {
                    node.add(hbox {
                        spacing = spacingVal
                        checkbox("Disqualify?") {
                            action { controller.toggleQualification(it) }
                        }
                        label(it.name)
                    })
                }
                count += 1
            }
        }
    }

    private fun reloadMatchesInto(node: Node) {
        clear(node)
        controller.refresh()
        renderMatchesInto(node)
        round = SimpleIntegerProperty(1)
    }

    private fun clear(node: Node) = node.getChildList()?.clear()

    private fun saveStateAs() {
        val fileList = chooseFile(
            "Save...",
            arrayOf(FileChooser.ExtensionFilter("JSON File", "*.json")),
            mode = FileChooserMode.Save
        )

        val model = controller.serializeToJson()

        try {
            controller.saveLocation = fileList[0].toPath()
            fileList[0].writeText(model)
        } catch (e: IndexOutOfBoundsException) {
            openInternalWindow(ErrorFragment(ErrorMessages.FILENOTCHOSEN.toString()))
        } catch (e: Exception) {
            openInternalWindow(ErrorFragment("${ErrorMessages.COULDNOTSAVE}: $e"))
        }
    }

    private fun loadPeopleFromCsv() {
        val fileList = chooseFile(
            "Choose a .csv file...",
            arrayOf(FileChooser.ExtensionFilter("CSV File", "*.csv"))
        )

        val service = controller.peopleService as PeopleServiceImpl

        try {
            service.fromCsv(fileList[0].reader())
        } catch (e: IndexOutOfBoundsException) {
            openInternalWindow(ErrorFragment(ErrorMessages.FILENOTCHOSEN.toString()))
        }

        reloadMatchesInto(matchesPane)
    }

    private fun saveState() {
        if (controller.saveLocation == null)
            saveStateAs()
        else {
            val model = controller.serializeToJson()

            try {
                controller.saveLocation!!.toFile().writeText(model)
            } catch (e: Exception) {
                openInternalWindow(ErrorFragment("${ErrorMessages.COULDNOTSAVE}: $e"))
            }
        }
    }

    private fun loadState() {
        val fileList = chooseFile(
            "Choose a .json file...",
            arrayOf(FileChooser.ExtensionFilter("JSON File", "*.json"))
        )

        try {
            controller.deserializeStringToModel(fileList[0].readText())
        } catch (e: IndexOutOfBoundsException) {
            openInternalWindow(ErrorFragment(ErrorMessages.FILENOTCHOSEN.toString()))
        }

        clear(matchesPane)
        round = SimpleIntegerProperty(1)
        renderMatchesInto(matchesPane)
    }

    override val root = borderpane {
        top {
            menubar {
                menu("Save") {


                    item("Save state as", "Shortcut+Shift+S").action {
                        saveStateAs()
                    }

                    item("Save state", "Shortcut+S").action {
                        saveState()
                    }


                }
                menu("Load") {
                    item("Load state from .json").action {
                        loadState()
                    }
                    item("Load people from .csv").action {
                        loadPeopleFromCsv()
                    }
                }
            }
        }
        center {
            vbox {
                spacing = spacingVal

                addClass(Styles.base)

                hbox {
                    spacing = spacingVal
                    alignment = Pos.BASELINE_CENTER

                    label("Matches").addClass(Styles.title1)

                }
                scrollpane {
                    spacing = spacingVal
                    paddingAll = 5.0

                    matchesPane = vbox {
                        spacing = spacingVal

                        renderMatchesInto(this)
                        round.addListener { _, _, _ -> renderMatchesInto(this) }
                    }
                }
            }
        }
        bottom {
            hbox {
                addClass(Styles.base)
                alignment = Pos.CENTER_LEFT
                spacing = spacingVal

                button("Refresh")
                    .action {
                        if (currControllerPeopleList != controller.peopleService.list) {
                            controller.refresh()
                            currControllerPeopleList = controller.peopleService.list.map { it.copy() }
                            round += 1
                            renderMatchesInto(matchesPane)
                        }
                    }
                }
            }
        }

    init { root.style = rootStyle }
}
