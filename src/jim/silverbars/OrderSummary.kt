package jim.silverbars

data class OrderSummary(val orderType: OrderType?, val quantity: Quantity?, val price: Money?) {
    operator fun plus(order: Order): OrderSummary {
        val type = orderType ?: order.orderType
        val price = price ?: order.price
        val quantity = order.quantity + quantity
        return OrderSummary(type, quantity, price)
    }

    companion object {
        fun empty(): OrderSummary {
            return OrderSummary(null, null, null)
        }
    }

    override fun toString(): String {
        return "$orderType $quantity for $price"
    }
}
