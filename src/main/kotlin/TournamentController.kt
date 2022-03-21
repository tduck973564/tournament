import tornadofx.Controller
import javafx.collections.FXCollections

class TournamentController: Controller() {
    val peopleService: IPeopleService by di()
    private var tournamentModel = TournamentModel.fromPersonCollection(peopleService.list)

    private fun qualify(person: Person) = peopleService.qualify(person)
    private fun disqualify(person: Person) = peopleService.disqualify(person)

    fun toggleQualification(person: Person)
        = if (person.qualified) disqualify(person) else qualify(person)

    var matches = FXCollections.observableArrayList(tournamentModel.matches)!!

    fun refresh() {
        tournamentModel = TournamentModel.fromPersonCollection(peopleService.list.filter { it.qualified })
        matches = FXCollections.observableArrayList(tournamentModel.matches)
    }
}