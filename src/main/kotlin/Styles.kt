import javafx.scene.text.FontWeight
import tornadofx.*

class Styles: Stylesheet() {
    companion object {
        val largeTitle by cssclass()
        val title1 by cssclass()
        val title2 by cssclass()
        val title3 by cssclass()

        val caption by cssclass()

        val base by cssclass()
    }

    init {
        largeTitle {
            fontSize = 26.px
        }

        title1 {
            fontWeight = FontWeight.BOLD
            fontSize = 22.px

            startMargin = 15.px
            endMargin = 15.px
        }
        title2 {
            fontSize = 17.px

            startMargin = 25.px
            endMargin = 25.px
        }
        title3 {
            fontSize = 15.px

            startMargin = 35.px
            endMargin = 35.px
        }

        caption {
            fontSize = 10.px

            startMargin = 35.px
            endMargin = 35.px
        }

        base {
            padding = box(10.px)
            fontSize = 13.px
        }
    }
}