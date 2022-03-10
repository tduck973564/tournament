import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.StringReader
import PeopleModel.Companion.Person

internal class PersonModelTest {
    private val csv = """
    Thomas
    Chungus
    That guy
    Fesd
    """.trimIndent()

    @Test
    fun `Person list from CSV must be equal to the model`() {
        Assertions.assertEquals(
            PeopleModel.fromCsv(
                StringReader(csv)),
            PeopleModel(mutableListOf(
                Person("Thomas"),
                Person("Chungus"),
                Person("That guy"),
                Person("Fesd"))
            )
        )
    }
}