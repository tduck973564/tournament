import PeopleModel.Companion.Person

data class MatchesModel(val matches: MutableList<Match>, val remainder: Person? = null) {
    companion object {
        data class Match(val person1: Person, val person2: Person)

        fun fromPeopleModel(people: PeopleModel): MatchesModel {
            val people = people.people.shuffled()
            val output: MutableList<Match> = mutableListOf()
            val loopLength =
                if (people.count() % 2 == 0) people.count() else people.count() - 1

            var i = 0; while (i < loopLength) {
                output.add(Match(people[i], people[i+1]))
                i += 2
            }

            return if (loopLength < people.count())
                MatchesModel(output, people[people.lastIndex])
            else
                MatchesModel(output, null)
        }
    }
}
