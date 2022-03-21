import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module
import tornadofx.launch

val mainModule = module {
    single { PeopleServiceImpl(
        mutableListOf(Person("Joe biden"), Person("MOHAMMAD RAjAB WALI"), Person("donald"), Person("sfgsds"))
    ) as IPeopleService }
}

fun main(args: Array<String>) {
    startKoin { modules(mainModule) }
    launch<TournamentApp>(args)
}