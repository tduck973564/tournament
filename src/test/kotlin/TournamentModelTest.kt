import org.junit.jupiter.api.Test

internal class TournamentModelTest {
    val evenPeople = PeopleServiceImpl(mutableListOf(
        Person("Thomas"),
        Person("Chungus"),
        Person("Fesd"),
        Person("Monkey")
    ))
    val oddPeople = evenPeople.apply { add(Person("Odd")) }

    @Test
    fun `Random matches are made from people (even)`() {
        for (i in TournamentModel.fromPersonCollection(evenPeople.list).matches)
            if (!(i.person1 in evenPeople.list && i.person2 in evenPeople.list))
                throw Exception()
    }

    @Test
    fun `Random matches are made from people (odd)`() {
        for (i in TournamentModel.fromPersonCollection(oddPeople.list).matches)
            if (!(i.person1 in oddPeople.list && i.person2 in oddPeople.list))
                throw Exception()
    }
}