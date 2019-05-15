package jim.silverbars

import java.math.BigDecimal

data class Quantity(val amount: BigDecimal) {
    operator fun plus(other: Quantity?): Quantity {
        other?.let { q ->
            return Quantity(amount.plus(q.amount))
        }
        return this
    }

    constructor(amount: String) : this(BigDecimal(amount))
}
