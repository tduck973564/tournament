import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.StringReader

internal class PersonModelTest {
    val csv = """
    Thomas
    Chungus
    That guy
    Fesd
    """.trimIndent()

    @Test
    fun `Person list from CSV must be in order with correct names and with people`() {
        Assertions.assertEquals(
            PersonModel.arrayFromCsv(
                StringReader(csv)),
            listOf(
                PersonModel("Thomas"),
                PersonModel("Chungus"),
                PersonModel("That guy"),
                PersonModel("Fesd")
            )
        )
    }
}