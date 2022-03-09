import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import java.io.Reader

data class PersonModel(val name: String, var qualified: Boolean = true) {
    companion object {
        fun arrayFromCsv(reader: Reader): List<PersonModel> {
            val csvParser = CSVParser(reader, CSVFormat.DEFAULT)

            return csvParser.map { PersonModel(it.get(0)) }

        }
    }
}