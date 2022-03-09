data class TournamentModel(
    var round: Int,
    var people: MutableList<PersonModel>,
    var matches: MutableList<MatchModel>
    )

class TournamentController {
    var state = TournamentModel(0, mutableListOf(), mutableListOf())

    //fun progress(disqualified())
}