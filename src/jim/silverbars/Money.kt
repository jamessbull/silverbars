package jim.silverbars

import java.math.BigDecimal

data class Money(val currency: Currency, val amount: BigDecimal) : Comparable<Money> {
    constructor(currency: Currency, amount: String) : this(currency, BigDecimal(amount))

    override fun compareTo(other: Money): Int = this.amount.compareTo(other.amount)
    override fun toString(): String = "$currency$amount"
}
