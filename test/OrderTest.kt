import jim.silverbars.Currency
import jim.silverbars.Money
import jim.silverbars.Order
import jim.silverbars.OrderType.BUY
import jim.silverbars.Quantity
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.Test
import java.math.BigDecimal

class OrderTest {
    @Test
    fun canCreateOrder() {
        val price = Money(Currency.GBP, BigDecimal("12.21"))
        val quantity = Quantity(BigDecimal("4.0"))
        val order = Order(orderType = BUY, quantity = quantity, price = price)
        assertThat(order.price, equalTo(price))
        assertThat(order.orderType, equalTo(BUY))
        assertThat(order.quantity, equalTo(Quantity(BigDecimal("4.0"))))
    }
}