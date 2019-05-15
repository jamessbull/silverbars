package jim.silverbars

import java.math.BigDecimal

class Quantity(amountString: String) {
    val amount = BigDecimal(amountString)
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Quantity

        if (amount != other.amount) return false

        return true
    }

    override fun hashCode(): Int {
        return amount.hashCode()
    }

}
