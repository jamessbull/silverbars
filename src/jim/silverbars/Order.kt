package jim.silverbars

class Order(
    val price: Money,
    val orderType: OrderType,
    val quantity: Quantity,
    val userid: String
)
