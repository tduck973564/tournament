import org.junit.jupiter.api.Test

internal class MatchModelTest {
    val evenPeople = listOf(
        PersonModel("Thomas"),
        PersonModel("Chungus"),
        PersonModel("Fesd"),
        PersonModel("Monkey")
    )
    val oddPeople = evenPeople + PersonModel("odd")

    @Test
    fun `Random matches are made from people (even)`() {
        for (i in MatchModel.arrayOfMatchesFromPersonArray(evenPeople).matches)
            if (!(i.person1 in evenPeople && i.person2 in evenPeople))
                throw Exception()
    }

    @Test
    fun `Random matches are made from people (odd)`() {
        for (i in MatchModel.arrayOfMatchesFromPersonArray(oddPeople).matches)
            if (!(i.person1 in oddPeople && i.person2 in oddPeople))
                throw Exception()
    }
}