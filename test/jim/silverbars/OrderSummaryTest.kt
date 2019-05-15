package jim.silverbars

import jim.silverbars.OrderType.BUY
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class OrderSummaryTest {
    @Test
    fun canGetEmptyOrderSummary() {
        val emptySummary = OrderSummary.empty()
        assertThat(emptySummary, equalTo(OrderSummary(null, null, null)))
    }

    @Test
    fun canAddOrderToOrderSummary() {
        val price = Money(Currency.GBP, "5")
        val quantity = Quantity("55")
        val order = Order(price, BUY, quantity, "Mary")
        assertThat(OrderSummary.empty() + order, equalTo(OrderSummary(BUY, quantity, price)))
    }

    @Test
    fun canAddManyOrdersToOrderSummary() {
        val price = Money(Currency.GBP, "5")
        val order = Order(price, BUY, Quantity("55"), "Mary")
        val order1 = Order(price, BUY, Quantity("45"), "Mary")

        val summary = OrderSummary.empty() + order + order1
        assertThat(summary, equalTo(OrderSummary(BUY, Quantity("100"), price)))
    }
}