package jim.silverbars


enum class OrderType(private val display: String) {
    BUY("Buy"),
    SELL("Sell");

    override fun toString(): String = display
}
