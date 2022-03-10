import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.StringReader

internal class PeopleServiceTest {
    private val csv = """
    Thomas
    Chungus
    That guy
    Fesd
    """.trimIndent()

    @Test
    fun `Person list from CSV must be equal to the service's model`() {
        Assertions.assertEquals(
            PeopleServiceImpl.fromCsv(
                StringReader(csv)).list,
            mutableListOf(
                Person("Thomas"),
                Person("Chungus"),
                Person("That guy"),
                Person("Fesd")
            )
        )
    }

    @Test
    fun `Service must disqualify person`() {
        Assertions.assertEquals(
            PeopleServiceImpl(mutableListOf(
                Person("Thomas"),
                Person("Chungus"),
                Person("That guy"),
                Person("Fesd"))).apply { disqualify(Person("Fesd")) }.list,
            mutableListOf(
                Person("Thomas"),
                Person("Chungus"),
                Person("That guy"),
                Person("Fesd", false)
            )
        )
    }

    @Test
    fun `Service must qualify person`() {
        Assertions.assertEquals(
            PeopleServiceImpl(mutableListOf(
                Person("Thomas"),
                Person("Chungus"),
                Person("That guy"),
                Person("Fesd", false))).apply { qualify(Person("Fesd")) }.list,
            mutableListOf(
                Person("Thomas"),
                Person("Chungus"),
                Person("That guy"),
                Person("Fesd", true)
            )
        )
    }
}