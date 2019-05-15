package jim.silverbars

import java.math.BigDecimal

data class Money(val currency: Currency, val amount: BigDecimal) : Comparable<Money> {
    override fun compareTo(other: Money): Int {
        return this.amount.compareTo(other.amount)
    }

    constructor(currency: Currency, amount: String) : this(currency, BigDecimal(amount))
}
