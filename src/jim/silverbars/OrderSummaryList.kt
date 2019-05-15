package jim.silverbars

fun List<OrderSummary>.toDisplay(): List<String> {
    return this.map { it.toString() }
}