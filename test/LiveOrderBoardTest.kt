import jim.silverbars.*
import jim.silverbars.Currency.GBP
import jim.silverbars.OrderType.BUY
import jim.silverbars.OrderType.SELL
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.contains
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

    fun anOrder(quantity: String, price: String, orderType: OrderType): Order {
        return Order(Money(GBP, price), orderType, Quantity(quantity), "Sue")
    }


}