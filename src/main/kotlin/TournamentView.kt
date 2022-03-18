import tornadofx.*
import javax.script.Bindings

class TournamentView : View() {

    val controller: TournamentController by inject()

    override val root = vbox {
        label("Matches")
        for (match in controller.matches) {
            tableview(listOf(match.person1, match.person2).asObservable()) {
                readonlyColumn("Name", Person::name)
                column("Qualified", Person::qualified)
            }
        }
        button("Refresh").action {
            controller.refresh()
        }
    }

init {
        root.style = "-fx-font-family: 'serif'"
    }
}
