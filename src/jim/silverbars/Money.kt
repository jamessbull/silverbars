package jim.silverbars

import java.math.BigDecimal

data class Money(val currency: Currency, val amount: BigDecimal) {
    constructor(currency: Currency, amount: String) : this(currency, BigDecimal(amount))
}
