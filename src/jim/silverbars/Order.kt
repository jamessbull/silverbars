package jim.silverbars

data class Order(
    val orderType: OrderType,
    val quantity: Quantity,
    val price: Money,
    val userid: String
)
