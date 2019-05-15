package jim.silverbars

import jim.silverbars.Currency.GBP
import jim.silverbars.OrderType.BUY
import jim.silverbars.OrderType.SELL
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Assert.assertThrows
import org.junit.Test

class LiveOrderBoardTest {
    @Test
    fun canRegisterOrder() {
        val board = LiveOrderBoard()
        val newBoard = board.register(anOrder("5.0", "300", BUY))

        assertThat(newBoard.orders, contains(anOrder("5.0", "300", BUY)))
    }

    @Test
    fun canRegisterMultipleOrders() {
        val board = LiveOrderBoard()
        val order1 = anOrder("5.0", "300", BUY)
        val order2 = anOrder("10.0", "200", BUY)
        val newBoard = board
            .register(order1)
            .register(order2)

        assertThat(newBoard.orders, contains(order1, order2))
    }

    @Test
    fun canCancelOrders() {
        val board = LiveOrderBoard()
        val order1 = anOrder("5.0", "300", BUY)
        val order2 = anOrder("10.0", "200", BUY)
        val newBoard = board
            .register(order1)
            .register(order2)
            .cancel(order1)

        assertThat(newBoard.orders, contains(order2))

    }

    @Test
    fun cancellingOrderWhichIsNotThereThrowsException() {
        val board = LiveOrderBoard()
        val order1 = anOrder("5.0", "300", BUY)

        assertThrows("Order not found", RuntimeException::class.java) {
            board.cancel(order1)
        }
    }

    @Test
    fun canGetBuyOrders() {
        val buyOrder = anOrder("2", "123.23", BUY)

        val board = LiveOrderBoard()
            .register(buyOrder)
            .register(anOrder("2", "123.23", SELL))

        assertThat(board.buyOrders(), contains(buyOrder))
    }

    @Test
    fun canGetSellOrders() {
        val sellOrder = anOrder("2", "123.23", SELL)

        val board = LiveOrderBoard()
            .register(sellOrder)
            .register(anOrder("2", "123.23", BUY))

        assertThat(board.sellOrders(), contains(sellOrder))
    }

    @Test
    fun noOrdersMeansNoSummaries() {
        val board = LiveOrderBoard()
        val orderSummaries: List<OrderSummary> = board.buyOrderSummaries()
        assertThat(orderSummaries, empty())
    }

    @Test
    fun twoBuyOrdersWithDifferentPricesIsTwoSummaries() {
        val buyOrder = anOrder("2", "123.23", BUY)
        val buyOrder2 = anOrder("3", "123.24", BUY)

        val board = LiveOrderBoard()
            .register(buyOrder)
            .register(buyOrder2)

        val summaryOne = OrderSummary(BUY, Quantity("2"), Money(GBP, "123.23"))
        val summaryTwo = OrderSummary(BUY, Quantity("3"), Money(GBP, "123.24"))

        val orderSummaries: List<OrderSummary> = board.buyOrderSummaries()

        assertThat(orderSummaries, hasSize(2))
        assertThat(orderSummaries, contains(summaryTwo, summaryOne))

    }

    @Test
    fun twoBuyOrdersWithSamePriceIsOneSummary() {
        val buyOrder = anOrder("2", "123.23", BUY)
        val buyOrder2 = anOrder("3", "123.23", BUY)

        val board = LiveOrderBoard()
            .register(buyOrder)
            .register(buyOrder2)

        val summaryOne = OrderSummary(BUY, Quantity("5"), Money(GBP, "123.23"))

        val orderSummaries: List<OrderSummary> = board.buyOrderSummaries()

        assertThat(orderSummaries, hasSize(1))
        assertThat(orderSummaries, contains(summaryOne))

    }

    @Test
    fun buyOrderSummariesAreOrderedByPriceDescending() {
        val buyOrder = anOrder("1", "125", BUY)
        val buyOrder1 = anOrder("2", "125", BUY)
        val buyOrder2 = anOrder("3", "1", BUY)
        val buyOrder3 = anOrder("4", "1", BUY)
        val buyOrder4 = anOrder("5", "200", BUY)
        val buyOrder5 = anOrder("6", "200", BUY)

        val orderSummary1 = OrderSummary(BUY, Quantity("11"), Money(GBP, "200"))
        val orderSummary2 = OrderSummary(BUY, Quantity("3"), Money(GBP, "125"))
        val orderSummary3 = OrderSummary(BUY, Quantity("7"), Money(GBP, "1"))

        val board = LiveOrderBoard()
            .register(buyOrder)
            .register(buyOrder1)
            .register(buyOrder2)
            .register(buyOrder3)
            .register(buyOrder4)
            .register(buyOrder5)

        assertThat(board.buyOrderSummaries(), contains(orderSummary1, orderSummary2, orderSummary3))
    }

    @Test
    fun sellOrderSummariesAreOrderedByPriceAscending() {
        val sellOrder = anOrder("1", "125", SELL)
        val sellOrder1 = anOrder("2", "125", SELL)
        val sellOrder2 = anOrder("3", "1", SELL)
        val sellOrder3 = anOrder("4", "1", SELL)
        val sellOrder4 = anOrder("5", "200", SELL)
        val sellOrder5 = anOrder("6", "200", SELL)

        val orderSummary1 = OrderSummary(SELL, Quantity("11"), Money(GBP, "200"))
        val orderSummary2 = OrderSummary(SELL, Quantity("3"), Money(GBP, "125"))
        val orderSummary3 = OrderSummary(SELL, Quantity("7"), Money(GBP, "1"))

        val board = LiveOrderBoard()
            .register(sellOrder)
            .register(sellOrder1)
            .register(sellOrder2)
            .register(sellOrder3)
            .register(sellOrder4)
            .register(sellOrder5)

        assertThat(board.sellOrderSummaries(), contains(orderSummary3, orderSummary2, orderSummary1))
    }

    @Test
    fun canGetAllSummariesInTheCorrectOrder() {

        val orders = listOf(
            anOrder("1", "1", SELL),
            anOrder("2", "1", SELL),
            anOrder("3", "2", SELL),
            anOrder("4", "2", SELL),
            anOrder("5", "1", BUY),
            anOrder("6", "1", BUY),
            anOrder("7", "2", BUY),
            anOrder("8", "2", BUY)
        )

        val board = orders.fold(LiveOrderBoard()) { board, order ->
            board.register(order)
        }

        val expectedOrderSummaries = listOf(
            OrderSummary(BUY, Quantity("15"), Money(GBP, "2")),
            OrderSummary(BUY, Quantity("11"), Money(GBP, "1")),
            OrderSummary(SELL, Quantity("3"), Money(GBP, "1")),
            OrderSummary(SELL, Quantity("7"), Money(GBP, "2"))
        )

        assertThat(board.allSummaries(), equalTo(expectedOrderSummaries))
    }

    fun anOrder(quantity: String, price: String, orderType: OrderType): Order {
        return Order(orderType, Quantity(quantity), Money(GBP, price), "Sue")
    }
}


