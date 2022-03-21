import javafx.beans.property.SimpleIntegerProperty
import javafx.geometry.Pos
import javafx.scene.Node
import javafx.scene.text.FontWeight
import tornadofx.*

class TournamentView : View("Tournament App") {
    private val controller: TournamentController by inject()
    // This crap on the end is here because it's shallow copied without it, utter insanity
    private var currControllerPeopleList = controller.peopleService.list.map { it.copy() }

    private var round = SimpleIntegerProperty(1)

    private val spacingVal = 10.0

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

    override val root = borderpane {
        top {
            menubar {
                menu("File") {
                    item("Load people from .csv").action {
                        // TODO: File selector window
                    }
                    item("Save state").action {
                        // TODO: File selector window, deserialize model to JSON
                    }
                    item("Load state from .json").action {
                        // TODO: File selector window, serialize JSON to TournamentModel and assign
                    }
                }
                menu("Edit") {
                    item("Undo").action {
                        // TODO: History feature, keybindings
                    }
                    item("Redo").action {
                        // TODO: History feature, keybindings
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

                    vbox {
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
                        }
                    }
                }
            }
        }

    init {
        root.style = "-fx-font-family: 'SF Pro', 'Helvetica Neue', 'Segoe UI Variable', 'Segoe UI', sans-serif;"
    }
}
