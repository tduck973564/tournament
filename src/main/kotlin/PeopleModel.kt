import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import java.io.Reader

data class PeopleModel(val people: MutableList<Person>) {
    companion object {
        data class Person(val name: String, var qualified: Boolean = true)

        fun fromCsv(reader: Reader): PeopleModel {
            val csvParser = CSVParser(reader, CSVFormat.DEFAULT)

            return PeopleModel(csvParser.map { Person(it.get(0)) }.toMutableList())
        }
    }
}