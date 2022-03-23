import tornadofx.*;

class ErrorFragment: Fragment() {
    val message: String by param("There was an unspecified error.")

    override val root = vbox {
        spacing = spacingVal
        addClass(Styles.base)

        label("Error").addClass(Styles.largeTitle)
        label(message)
    }
}