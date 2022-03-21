import javafx.scene.text.FontWeight
import tornadofx.Stylesheet
import tornadofx.cssclass
import tornadofx.px

class Styles: Stylesheet() {
    companion object {
        val h1 by cssclass()
        val h2 by cssclass()
        val h3 by cssclass()

        val root by cssclass()
    }

    init {
        h1 {
            fontWeight = FontWeight.BOLD
            fontSize = 25.px

            startMargin = 15.px
            endMargin = 15.px
        }
        h2 {
            fontSize = 20.px

            startMargin = 25.px
            endMargin = 25.px
        }
        h3 {
            fontSize = 15.px

            startMargin = 35.px
            endMargin = 35.px
        }

        root {
            //padding = box(10.px)
            //labelPadding = padding

            //startMargin = 10.px
            //endMargin = 10.px
        }
    }
}