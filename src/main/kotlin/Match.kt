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
        fun arrayOfMatchesFromPersonArray(people: List<Person>): List<Match> {
            val people = people.shuffled()
            val output: MutableList<Match> = mutableListOf()

            var i = 0; while (i < people.count()) {

                output.add(Match(people[i], people[i+1]))
                i += 2
            }

            return output.toList()
        }
    }
}
