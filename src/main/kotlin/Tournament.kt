data class TournamentState(
    var round: Int,
    var people: MutableList<Person>,
    var matches: MutableList<Match>
    )

class Tournament {
    var state = TournamentState(0, mutableListOf(), mutableListOf())

    //fun progress(disqualified())
}