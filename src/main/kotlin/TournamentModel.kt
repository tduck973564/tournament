import kotlinx.serialization.Serializable
import java.nio.file.Path

@Serializable
data class TournamentModel(
    val matches: MutableList<Match>,
    val remainder: Person? = null,
    @Serializable(with = PathAsStringSerializer::class)
    var saveLocation: Path? = null,
) {
    companion object {
        @Serializable
        data class Match(val person1: Person, val person2: Person)

        fun fromPersonCollection(people: Collection<Person>): TournamentModel {
            val people = people.shuffled()
            val output: MutableList<Match> = mutableListOf()
            val loopLength =
                if (people.count() % 2 == 0) people.count() else people.count() - 1

            var i = 0; while (i < loopLength) {
                output.add(Match(people[i], people[i+1]))
                i += 2
            }

            return if (loopLength < people.count())
                TournamentModel(output, people[people.lastIndex])
            else
                TournamentModel(output, null)
        }
    }
}
