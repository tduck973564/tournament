import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module
import tornadofx.launch

val mainModule = module {
    single { PeopleServiceImpl() as IPeopleService }
}

fun main(args: Array<String>) {
    startKoin { modules(mainModule) }
    launch<TournamentApp>(args)
}