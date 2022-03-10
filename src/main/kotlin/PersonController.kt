import PeopleModel.Companion.Person

class PersonController: KoinComponent {
    var people by inject<PeopleModel>()

    fun disqualify(people: List<Person>) {
    }
}