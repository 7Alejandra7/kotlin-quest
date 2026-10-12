
class Monster(
    name: String,
    maxHealth: Int,
    baseAttack: Int
) : Character(
    name = name,
    maxHealth = maxHealth,
    baseAttack = baseAttack
) {
    private var hasHealed = false

    fun takeTurn(target: Character) {
        if (!isAlive || !target.isAlive) return

        if (currentHealth <= 25 && !hasHealed) {
            val healingChance = randomBetween(1, 2)

            if (healingChance == 1) {
                hasHealed = true
                val healingAmount = randomBetween(12, 20)

                warning("\n🧪 ¡El Goblin usa una poción de emergencia!")
                heal(healingAmount)
                return
            }
        }

        var damage = randomBetween(7, 16)

        if (damage == 16) {
            damage = 20
            danger("\n💢 ¡Golpe crítico del Goblin!")
        }

        println("\n👹 El Goblin ataca a ${target.name}.")
        target.receiveDamage(damage)
    }
}
