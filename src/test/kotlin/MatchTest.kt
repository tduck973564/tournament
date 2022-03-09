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
        for (i in Match.arrayOfMatchesFromPersonArray(evenPeople).matches)
            if (!(i.person1 in evenPeople && i.person2 in evenPeople))
                throw Exception()
    }

    @Test
    fun `Random matches are made from people (odd)`() {
        for (i in Match.arrayOfMatchesFromPersonArray(oddPeople).matches)
            if (!(i.person1 in oddPeople && i.person2 in oddPeople))
                throw Exception()
    }
}