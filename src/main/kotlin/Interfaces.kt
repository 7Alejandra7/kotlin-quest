interface Damageable {
    fun receiveDamage(amount: Int)
}

interface Healable {
    fun heal(amount: Int): Int
}
