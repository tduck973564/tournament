import javafx.stage.Stage
import org.koin.core.context.GlobalContext.get
import tornadofx.*
import kotlin.reflect.KClass

class TournamentApp: App(TournamentView::class, Styles::class) {
    override fun start(stage: Stage) {
        with(stage) {
            minWidth = 600.0
            minHeight = 400.0
            super.start(this)
        }
    }

    init {
        FX.dicontainer = object : DIContainer {
            override inline fun <T : Any> getInstance(type: KClass<T>)
                = get().get<T>(type)
        }
    }
}