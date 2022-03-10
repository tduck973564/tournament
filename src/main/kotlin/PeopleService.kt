import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import java.io.Reader

data class Person(val name: String, var qualified: Boolean = true)

interface IPeopleService {
    var list: MutableList<Person>

    fun add(personToAdd: Person)
    fun remove(personToRemove: Person)
    fun disqualify(personToDisqualify: Person)
    fun qualify(personToQualify: Person)
    fun getQualified(): List<Person>
    fun getDisqualified(): List<Person>
}

class PeopleServiceImpl(override var list: MutableList<Person>): IPeopleService {

    override fun add(personToAdd: Person) {
        list.add(personToAdd)
    }

    override fun remove(personToRemove: Person) {
        list.remove(personToRemove)
    }

    override fun disqualify(personToDisqualify: Person) {
        list.map {
            if (it.name == personToDisqualify.name) it.qualified = false
            else it
        }
    }

    override fun qualify(personToQualify: Person) {
        list.map {
            if (it.name == personToQualify.name) it.qualified = true
            else it
        }
    }

    override fun getQualified() =
        list.filter { it.qualified }

    override fun getDisqualified() =
        list.filter { !it.qualified }

    companion object {
        fun fromCsv(reader: Reader): PeopleServiceImpl {
            val csvParser = CSVParser(reader, CSVFormat.DEFAULT)

            return PeopleServiceImpl(csvParser.map { Person(it.get(0)) }.toMutableList())
        }
    }
}