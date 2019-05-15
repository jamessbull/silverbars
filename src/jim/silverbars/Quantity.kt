package jim.silverbars

import java.math.BigDecimal

data class Quantity(val amount: BigDecimal) {
    constructor(amount: String) : this(BigDecimal(amount))
}
