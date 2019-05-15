package jim.silverbars

import java.math.BigDecimal

data class Quantity(val amount: BigDecimal) {
    constructor(amount: String) : this(BigDecimal(amount))

    operator fun plus(other: Quantity?): Quantity {
        return other?.let { q -> Quantity(amount.plus(q.amount)) } ?: this
    }

    override fun toString(): String = "${amount}Kg"
}
