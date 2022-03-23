import tornadofx.Controller
import javafx.collections.FXCollections
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString
import java.nio.file.Path

class TournamentController: Controller() {
    val peopleService: IPeopleService by di()
    private var model = TournamentModel.fromPersonCollection(peopleService.list)

    var matches = FXCollections.observableArrayList(model.matches)!!

    private fun qualify(person: Person) = peopleService.qualify(person)
    private fun disqualify(person: Person) = peopleService.disqualify(person)

    fun toggleQualification(person: Person)
        = if (person.qualified) disqualify(person) else qualify(person)

    fun serializeToJson() = Json.encodeToString(model)
    fun deserializeStringToModel(string: String) { model = Json.decodeFromString(string) }

    var saveLocation: Path?
        get() = model.saveLocation
        set(value) { model.saveLocation = value}

    fun refresh() {
        model = TournamentModel.fromPersonCollection(peopleService.list.filter { it.qualified })
        matches = FXCollections.observableArrayList(model.matches)
    }
}