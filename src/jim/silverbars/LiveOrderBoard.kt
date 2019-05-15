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

    fun sellOrderSummaries(): List<OrderSummary> =
        sellOrders()
            .groupingBy { it.price }
            .fold(OrderSummary.empty()) { summary, order ->
                summary + order
            }.values.sortedBy { it.price }

    fun buyOrderSummaries(): List<OrderSummary> =
        buyOrders()
            .groupingBy { it.price }
            .fold(OrderSummary.empty()) { summary, item ->
                summary + item
            }.values.sortedByDescending { it.price }

    fun allSummaries(): List<OrderSummary> = buyOrderSummaries() + sellOrderSummaries()
}
