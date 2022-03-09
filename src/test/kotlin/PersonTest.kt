import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.StringReader

internal class PersonTest {
    val csv = """
    Thomas
    Chungus
    That guy
    Fesd
    """.trimIndent()

    @Test
    fun `Person list from CSV must be in order with correct names and with people`() {
        Assertions.assertEquals(
            Person.arrayFromCsv(
                StringReader(csv)),
            listOf(
                Person("Thomas"),
                Person("Chungus"),
                Person("That guy"),
                Person("Fesd")
            )
        )
    }
}