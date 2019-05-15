import jim.silverbars.*
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.contains
import org.junit.Test
import java.math.BigDecimal

class LiveOrderBoardTest {
    @Test
    fun canRegisterOrder() {
        val board = LiveOrderBoard()
        val newOrder = Order(Money(Currency.GBP, BigDecimal("5.0")), OrderType.BUY, Quantity("4.0"), "Sue")
        val newBoard = board.register(newOrder)

        assertThat(newBoard.orders, contains(newOrder))
    }

}