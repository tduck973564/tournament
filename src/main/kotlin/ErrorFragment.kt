import tornadofx.*

class ErrorFragment(private val message: String = "There was an unspecified error."): Fragment() {

    override val root = vbox {
        spacing = spacingVal
        addClass(Styles.base)

        label("Error").addClass(Styles.title1)
        label(message)

        button("Close").action { close() }
    }

    init { root.style = rootStyle }
}