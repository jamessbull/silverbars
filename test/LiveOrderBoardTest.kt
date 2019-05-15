import jim.silverbars.Currency.GBP
import jim.silverbars.LiveOrderBoard
import jim.silverbars.Money
import jim.silverbars.Order
import jim.silverbars.OrderType.BUY
import jim.silverbars.Quantity
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.contains
import org.hamcrest.Matchers.equalTo
import org.junit.Test

class LiveOrderBoardTest {
    @Test
    fun canRegisterOrder() {
        val board = LiveOrderBoard()
        val newBoard = board.register(anOrder("5.0", "300"))

        assertThat(newBoard.orders, contains(equalTo(anOrder("5.0", "300"))))
    }

    fun anOrder(quantity: String, price: String): Order {
        return Order(Money(GBP, price), BUY, Quantity(quantity), "Sue")
    }

}