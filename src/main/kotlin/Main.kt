import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module
import tornadofx.launch

const val spacingVal = 10.0
const val rootStyle = "-fx-font-family: 'SF Pro', 'Helvetica Neue', 'Segoe UI Variable', 'Segoe UI', sans-serif;"

val mainModule = module {
    single { PeopleServiceImpl(mutableListOf(Person("Joe"), Person("Joegb"), Person("sdfsdf"), Person("asdae"), Person("Jjh"), Person("Jdfsdf"), Person("Jobdbrdge"), Person("Jol.kcxzk,xze"))) as IPeopleService }
}

fun main(args: Array<String>) {
    startKoin { modules(mainModule) }
    launch<TournamentApp>(args)
}