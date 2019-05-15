import jim.silverbars.Currency
import jim.silverbars.Money
import jim.silverbars.Order
import jim.silverbars.OrderType.BUY
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.Test
import java.math.BigDecimal

class OrderTest {
    @Test
    fun canCreateOrder() {
        val price = Money(Currency.GBP, BigDecimal("12.21"))
        val order = Order(orderType = BUY, price = price)
        assertThat(order.price, equalTo(price))
        assertThat(order.orderType, equalTo(BUY))
    }
}