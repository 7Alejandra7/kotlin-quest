
open class Character(
    val name: String,
    val maxHealth: Int,
) : Damageable, Healable {

    var currentHealth: Int = maxHealth
        private set

    val isAlive: Boolean
        get() = currentHealth > 0

    var healingUses: Int = 3
        private set

    override fun receiveDamage(amount: Int) {
        val damage = amount.coerceAtLeast(0)
        currentHealth = (currentHealth - damage).coerceAtLeast(0)

        danger("\n$name recibe $damage puntos de daño.")
        showHealth()

        if (!isAlive) {
            danger("¡$name ha sido derrotado!")
        }
    }

    override fun heal(amount: Int): Int {
        if (!isAlive) return 0

        val previousHealth = currentHealth
        currentHealth = (currentHealth + amount.coerceAtLeast(0))
            .coerceAtMost(maxHealth)

        val actualHealing = currentHealth - previousHealth

        success("\n$name recupera $actualHealing HP.")
        showHealth()

        return actualHealing
    }

    open fun attack(target: Character) {
        if (!isAlive || !target.isAlive) return

        val damage = randomBetween(8, 15)
        println("\n$name realiza un ataque básico.")
        target.receiveDamage(damage)
    }

    fun healCommon() {
        if (!isAlive || healingUses <= 0 || currentHealth == maxHealth) {
            return
        }

        healingUses--
        heal(20)
        println("Curaciones restantes: $healingUses/3")
    }

    fun showHealth() {
        val filledHearts =
            (currentHealth * 10 + maxHealth - 1) / maxHealth
        val emptyHearts = 10 - filledHearts

        val healthColor = when {
            currentHealth <= maxHealth / 4 -> Colors.RED
            currentHealth <= maxHealth / 2 -> Colors.YELLOW
            else -> Colors.GREEN
        }

        println(
            "$name: $healthColor$currentHealth/$maxHealth HP${Colors.RESET}"
        )
        println("❤️".repeat(filledHearts) + "🖤".repeat(emptyHearts))
    }
}
