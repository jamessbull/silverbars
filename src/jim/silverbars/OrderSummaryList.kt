package jim.silverbars

fun List<OrderSummary>.toDisplay(): List<String> = this.map { it.toString() }
