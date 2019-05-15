package jim.silverbars

fun List<Order>.toOrderSummaries(): List<OrderSummary> {
    return this.groupingBy { o -> o.price }
        .fold(OrderSummary.empty()) { summary, order ->
            summary + order
        }.values.toList()
}