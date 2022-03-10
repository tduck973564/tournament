data class TournamentModel(
    var round: Int,
    var people: PeopleModel,
    var matches: MatchesModel
    )

class TournamentController {
    var state = TournamentModel(0, PeopleModel(mutableListOf()), MatchesModel(mutableListOf()))

    //fun progress(disqualified())
}