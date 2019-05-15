package jim.silverbars

data class Order(
    val price: Money,
    val orderType: OrderType,
    val quantity: Quantity,
    val userid: String
)
