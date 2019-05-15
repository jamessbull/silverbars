package jim.silverbars

enum class Currency(private val display: String) {
    GBP("£");

    override fun toString(): String {
        return display
    }
}