import tornadofx.Controller
import javafx.collections.FXCollections

class TournamentController: Controller() {
    val peopleService: IPeopleService by di()
    var tournamentModel = TournamentModel.fromPersonCollection(peopleService.list)

    fun qualify(person: Person) = peopleService.qualify(person)
    fun disqualify(person: Person) = peopleService.disqualify(person)

    var matches = FXCollections.observableArrayList(tournamentModel.matches)

    fun refresh() {
        tournamentModel = TournamentModel.fromPersonCollection(peopleService.list)
        matches = FXCollections.observableArrayList(tournamentModel.matches)
    }
}