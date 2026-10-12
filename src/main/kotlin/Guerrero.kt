
class Warrior(name: String) : Character(
    name = name,
    maxHealth = 100,
    baseAttack = 8
) {
    var energy: Int = 3
        private set

    override fun attack(target: Character) {
        if (!isAlive || !target.isAlive) return

        val damage = randomBetween(8, 15)

        println("\n⚔️ $name realiza un ataque básico.")
        target.receiveDamage(damage)

        energy = (energy + 1).coerceAtMost(3)
        success("⚡ Energía: $energy/3")
    }

    fun powerStrike(target: Character) {
        if (!isAlive || !target.isAlive || energy < 2) return

        energy -= 2
        val damage = randomBetween(18, 30)

        magic("\n💥 ¡$name usa Golpe Poderoso!")
        target.receiveDamage(damage)
        success("⚡ Energía restante: $energy/3")
    }
}
