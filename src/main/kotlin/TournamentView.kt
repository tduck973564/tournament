import javafx.beans.property.SimpleIntegerProperty
import javafx.scene.Node
import javafx.scene.text.FontWeight
import tornadofx.*

class TournamentView : View() {
    private val controller: TournamentController by inject()
    // This crap on the end is here because it's shallow copied without it, utter insanity
    private var currControllerPeopleList = controller.peopleService.list.map { it.copy() }

    private var round = SimpleIntegerProperty(1)

    private fun renderMatchesInto(node: Node) {
        var count = 1

        node.add(label("Round ${round.value}").addClass(Styles.h2))

        if (controller.matches.isEmpty()) {
            node.add(label("No more matches!").addClass(Styles.h3).apply {
                style { fontWeight = FontWeight.NORMAL }
            })
            node.add(label("${controller.peopleService.getWinner()} is the winner!").addClass(Styles.h3).apply {
                style { fontWeight = FontWeight.NORMAL }
            })
        } else {
            for (match in controller.matches) {

                node.add(label("Match $count").addClass(Styles.h3))

                listOf(match.person1, match.person2).asObservable().forEach {
                    node.add(hbox {
                        checkbox("Disqualify?  ") {
                            action { controller.toggleQualification(it) }
                        }
                        label(it.name)
                    })
                }
                count += 1
            }
        }
    }

    override val root = vbox {
        addClass(Styles.root)

        hbox {
            label("Matches  ").addClass(Styles.h1)
            button("Refresh").action {
                if (currControllerPeopleList != controller.peopleService.list) {
                    controller.refresh()
                    currControllerPeopleList = controller.peopleService.list.map { it.copy() }
                    round += 1
                }
            }
        }
        scrollpane {
            vbox {
                renderMatchesInto(this)
                round.addListener { _, _, _ -> renderMatchesInto(this) }
            }
        }
    }

init {
        root.style = "-fx-font-family: 'serif';"
    }
}
