import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module
import tornadofx.launch

val spacingVal = 10.0

val mainModule = module {
    single { PeopleServiceImpl(mutableListOf(Person("Joe"), Person("Joegb"), Person("sdfsdf"), Person("asdae"), Person("Jjh"), Person("Jdfsdf"), Person("Jobdbrdge"), Person("Jol.kcxzk,xze"))) as IPeopleService }
}

fun main(args: Array<String>) {
    startKoin { modules(mainModule) }
    launch<TournamentApp>(args)
}