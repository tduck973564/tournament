import org.junit.jupiter.api.Test
import PeopleModel.Companion.Person

internal class MatchModelTest {
    val evenPeople = PeopleModel(mutableListOf(
        Person("Thomas"),
        Person("Chungus"),
        Person("Fesd"),
        Person("Monkey")
    ))
    val oddPeople =
        evenPeople.apply { people.add(Person("odd")) }

    @Test
    fun `Random matches are made from people (even)`() {
        for (i in MatchesModel.fromPeopleModel(evenPeople).matches)
            if (!(i.person1 in evenPeople.people && i.person2 in evenPeople.people))
                throw Exception()
    }

    @Test
    fun `Random matches are made from people (odd)`() {
        for (i in MatchesModel.fromPeopleModel(oddPeople).matches)
            if (!(i.person1 in oddPeople.people && i.person2 in oddPeople.people))
                throw Exception()
    }
}