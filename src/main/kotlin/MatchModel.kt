data class MatchModel(val person1: PersonModel, val person2: PersonModel) {
    companion object {

        data class MatchesWithRemainder(val matches: List<MatchModel>, val remainder: PersonModel?)

        fun arrayOfMatchesFromPersonArray(people: List<PersonModel>): MatchesWithRemainder {
            val people = people.shuffled()
            val output: MutableList<MatchModel> = mutableListOf()
            val loopLength =
                if (people.count() % 2 == 0) people.count() else people.count() - 1

            var i = 0; while (i < loopLength) {

                output.add(MatchModel(people[i], people[i+1]))
                i += 2
            }

            return if (loopLength < people.count())
                MatchesWithRemainder(output.toList(), people[people.lastIndex])
            else
                MatchesWithRemainder(output.toList(), null)
        }
    }
}
