import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import java.io.Reader

data class Person(val name: String, var qualified: Boolean = true) {
    companion object {
         fun arrayFromCsv(reader: Reader): List<Person> {
             val csvParser = CSVParser(reader, CSVFormat.DEFAULT)

             return csvParser.map { Person(it.get(0)) }

         }
     }
}

data class Match(val person1: Person, val person2: Person) {
    companion object {

        data class MatchesWithRemainder(val matches: List<Match>, val remainder: Person?)

        fun arrayOfMatchesFromPersonArray(people: List<Person>): MatchesWithRemainder {
            val people = people.shuffled()
            val output: MutableList<Match> = mutableListOf()
            val loopLength =
                if (people.count() % 2 == 0) people.count() else people.count() - 1

            var i = 0; while (i < loopLength) {

                output.add(Match(people[i], people[i+1]))
                i += 2
            }

            return if (loopLength < people.count())
                MatchesWithRemainder(output.toList(), people[people.lastIndex])
            else
                MatchesWithRemainder(output.toList(), null)
        }
    }
}
