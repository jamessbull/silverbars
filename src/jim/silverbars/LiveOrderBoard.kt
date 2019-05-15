package jim.silverbars

import jim.silverbars.OrderType.BUY
import jim.silverbars.OrderType.SELL

class LiveOrderBoard(val orders: List<Order> = emptyList()) {

    fun register(order: Order): LiveOrderBoard = LiveOrderBoard(orders + order)

    fun cancel(order: Order): LiveOrderBoard {
        orders.find { it == order } ?: throw RuntimeException("Order not found")
        return LiveOrderBoard(orders.filter { it != order })
    }

    fun buyOrders(): List<Order> = orders.filter { it.orderType == BUY }
    fun sellOrders(): List<Order> = orders.filter { it.orderType == SELL }

}
