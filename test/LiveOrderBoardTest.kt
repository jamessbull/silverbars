import jim.silverbars.Currency.GBP
import jim.silverbars.LiveOrderBoard
import jim.silverbars.Money
import jim.silverbars.Order
import jim.silverbars.OrderType.BUY
import jim.silverbars.Quantity
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.contains
import org.junit.Assert.assertThrows
import org.junit.Test

class LiveOrderBoardTest {
    @Test
    fun canRegisterOrder() {
        val board = LiveOrderBoard()
        val newBoard = board.register(anOrder("5.0", "300"))

        assertThat(newBoard.orders, contains(anOrder("5.0", "300")))
    }

    @Test
    fun canRegisterMultipleOrders() {
        val board = LiveOrderBoard()
        val order1 = anOrder("5.0", "300")
        val order2 = anOrder("10.0", "200")
        val newBoard = board
            .register(order1)
            .register(order2)

        assertThat(newBoard.orders, contains(order1, order2))
    }

    @Test
    fun canCancelOrders() {
        val board = LiveOrderBoard()
        val order1 = anOrder("5.0", "300")
        val order2 = anOrder("10.0", "200")
        val newBoard = board
            .register(order1)
            .register(order2)
            .cancel(order1)

        assertThat(newBoard.orders, contains(order2))

    }

    @Test
    fun cancellingOrderWhichIsNotThereThrowsException() {
        val board = LiveOrderBoard()
        val order1 = anOrder("5.0", "300")

        assertThrows("Order not found", RuntimeException::class.java) {
            board.cancel(order1)
        }
    }

    fun anOrder(quantity: String, price: String): Order {
        return Order(Money(GBP, price), BUY, Quantity(quantity), "Sue")
    }

}