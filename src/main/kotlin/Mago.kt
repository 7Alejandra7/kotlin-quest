
class Mage(name: String) : Character(
    name = name,
    maxHealth = 90,
) {
    var mana: Int = 40
        private set

    override fun attack(target: Character) {
        if (!isAlive || !target.isAlive) return

        val damage = randomBetween(6, 12)

        println("\n🪄 $name realiza un ataque básico.")
        target.receiveDamage(damage)
    }

    fun castSpell(target: Character) {
        if (!isAlive || !target.isAlive || mana < 10) return

        mana -= 10
        val damage = randomBetween(18, 30)

        magic("\n🔥 ¡$name lanza una Bola de Fuego!")
        target.receiveDamage(damage)
        showMana()
    }

    fun castHealingSpell() {
        if (!isAlive || mana < 15 || currentHealth == maxHealth) return

        mana -= 15
        val healingAmount = randomBetween(20, 30)

        magic("\n✨ ¡$name utiliza Curación Mágica!")
        heal(healingAmount)
        showMana()
    }

    fun showMana() {
        println("${Colors.BLUE}🔵 Maná: $mana/40${Colors.RESET}")
    }
}
