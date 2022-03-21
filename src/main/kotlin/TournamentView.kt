import tornadofx.*

class TournamentView : View() {

    val controller: TournamentController by inject()

    override val root = vbox {
        label("Matches").addClass(Styles.h1)
        var round = 1

        fun renderMatches() {
            var count = 1

            label("Round $round").addClass(Styles.h2)

            for (match in controller.matches) {
                label("Match $count").addClass(Styles.h3)
                for (person in listOf(match.person1, match.person2).asObservable()) {
                    hbox {
                        label(person.name)
                        label(person.qualified.toString())
                        checkbox("Qualified") {
                            action {
                                controller.toggleQualification(person)
                            }
                        }
                    }
                }
                count += 1
            }
            round += 1
        }

        button("Refresh").action {
            controller.refresh()
            renderMatches()
        }
    }

init {
        root.style = "-fx-font-family: 'serif'; padding: 10px;"
    }
}
