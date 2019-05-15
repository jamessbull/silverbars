package jim.silverbars

class LiveOrderBoard(val orders: List<Order> = emptyList()) {
    fun register(order: Order): LiveOrderBoard {
        return LiveOrderBoard(orders + order)
    }

}
