import javafx.scene.text.FontWeight
import tornadofx.*

class Styles: Stylesheet() {
    companion object {
        val h1 by cssclass()
        val h2 by cssclass()
        val h3 by cssclass()
    }

    init {
        h1 {
            fontWeight = FontWeight.BOLD
            fontSize = 30.px
        }
        h2 {
            fontWeight = FontWeight.BOLD
            fontSize = 25.px
        }
        h3 {
            fontWeight = FontWeight.BOLD
            fontSize = 20.px
        }
    }
}