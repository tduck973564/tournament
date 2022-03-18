import org.koin.core.context.GlobalContext.get
import tornadofx.*
import kotlin.reflect.KClass

class TournamentApp: App(TournamentView::class, Styles::class) {
    init {
        FX.dicontainer = object : DIContainer {
            override inline fun <T : Any> getInstance(type: KClass<T>)
                = get().get<T>(type)
        }
    }
}