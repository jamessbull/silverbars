package jim.silverbars


enum class OrderType(private val display: String) {
    BUY("Buy"),
    SELL("sell");

    override fun toString(): String {
        return display
    }

}
