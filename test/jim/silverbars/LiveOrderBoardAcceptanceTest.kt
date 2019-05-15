package jim.silverbars

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class LiveOrderBoardAcceptanceTest {
    @Test
    fun canSummariseAndDisplayOrders() {
        val orders = listOf(
            Order(OrderType.SELL, Quantity("3.5"), Money(Currency.GBP, "306"), "user1"),
            Order(OrderType.SELL, Quantity("1.2"), Money(Currency.GBP, "310"), "user2"),
            Order(OrderType.SELL, Quantity("1.5"), Money(Currency.GBP, "307"), "user3"),
            Order(OrderType.SELL, Quantity("2.0"), Money(Currency.GBP, "306"), "user4"),
            Order(OrderType.BUY, Quantity("2.1"), Money(Currency.GBP, "301"), "user8"),
            Order(OrderType.BUY, Quantity("2.5"), Money(Currency.GBP, "306"), "user10"),
            Order(OrderType.BUY, Quantity("2.7"), Money(Currency.GBP, "301"), "user5"),
            Order(OrderType.BUY, Quantity("2.9"), Money(Currency.GBP, "306"), "user6")
        )

        val expectedDisplayValues = listOf(
            "Buy 5.4Kg for £306",
            "Buy 4.8Kg for £301",
            "Sell 5.5Kg for £306",
            "Sell 1.5Kg for £307",
            "Sell 1.2Kg for £310"
        )

        val board = orders.fold(LiveOrderBoard()) { board, order ->
            board.register(order)
        }

        assertThat(board.allSummaries().toDisplay(), equalTo(expectedDisplayValues))


        val ordersToDelete = listOf(
            Order(OrderType.BUY, Quantity("2.1"), Money(Currency.GBP, "301"), "user8"),
            Order(OrderType.BUY, Quantity("2.5"), Money(Currency.GBP, "306"), "user10"),
            Order(OrderType.BUY, Quantity("2.7"), Money(Currency.GBP, "301"), "user5"),
            Order(OrderType.BUY, Quantity("2.9"), Money(Currency.GBP, "306"), "user6")
        )

        val expectedDisplayValuesAfterDeletion = listOf(
            "Sell 5.5Kg for £306",
            "Sell 1.5Kg for £307",
            "Sell 1.2Kg for £310"
        )

        val deletedOrdersBoard = ordersToDelete.fold(board) { deletionBoard, order ->
            deletionBoard.cancel(order)
        }

        assertThat(deletedOrdersBoard.allSummaries().toDisplay(), equalTo(expectedDisplayValuesAfterDeletion))

    }
}


