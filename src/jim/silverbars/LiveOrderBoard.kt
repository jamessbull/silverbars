package jim.silverbars

class LiveOrderBoard(val orders: List<Order> = emptyList()) {
    fun register(order: Order): LiveOrderBoard {
        return LiveOrderBoard(orders + order)
    }

    fun cancel(order: Order): LiveOrderBoard {
        orders.find { it == order } ?: throw RuntimeException("Order not found")
        return LiveOrderBoard(orders.filter {
            it != order
        })
    }

}
