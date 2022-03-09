import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class MatchTest {
    val evenPeople = listOf(
        Person("Thomas"),
        Person("Chungus"),
        Person("Fesd"),
        Person("Monkey")
    )
    val oddPeople = evenPeople + Person("odd")

    @Test
    fun `Random matches are made from people (even)`() {
    // TODO
    }

    @Test
    fun `Random matches are made from people (odd)`() {
    // TODO
    }
}