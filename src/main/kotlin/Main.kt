
fun main() {
    title("⚔️ KOTLIN QUEST ⚔️")

    title("📜 REGLAS DEL JUEGO")
    println("❤️ Reduce los HP del Goblin a cero para ganar.")
    println("⚔️ Cada ataque causa daño dentro de su rango.")
    println("🧪 Las curaciones recuperan vida.")
    println("👹 El Goblin puede usar una poción una sola vez.")

    var choice: Int

    do {
        println("\nElige tu personaje:")
        println("1. 🛡️ Guerrero")
        println("2. 🔮 Mago")
        print("Opción: ")

        choice = readln().toIntOrNull() ?: 0

        if (choice !in 1..2) {
            warning("Opción no válida. Inténtalo otra vez.")
        }
    } while (choice !in 1..2)

    val player: Character = when (choice) {
        1 -> Warrior("Thor")
        else -> Mage("Merlin")
    }

    val monster = Monster(
        name = "Goblin",
        maxHealth = 80
    )

    title("👹 ¡APARECIÓ UN GOBLIN!")
    println("¡${player.name}, prepárate para combatir!")

    println("\nPresiona Enter para comenzar...")
    readln()

    while (player.isAlive && monster.isAlive) {
        title("🗡️ TU TURNO")

        println("\n❤️ ${player.name}: ${player.currentHealth}/${player.maxHealth} HP")

        if (player is Warrior) {
            println("⚡ Energía: ${player.energy}/3")
        }

        if (player is Mage) {
            println("🔵 Maná: ${player.mana}/40")
        }

        println("👹 Goblin: ${monster.currentHealth}/${monster.maxHealth} HP")
        println()
        println("Elige una acción:\n")

        println("1. ⚔️ Ataque básico")

        if (player is Warrior) {
            println("   Daño: 8–15 | Recupera 1 ⚡ de energía")
            println("2. 💥 Golpe poderoso (18–30 daño | cuesta 2 ⚡)")
            println("3. 🧪 Curación común (hasta 20 HP | usos: ${player.healingUses}/3)")
        }

        if (player is Mage) {
            println("   Daño: 6–12")
            println("2. 🔥 Bola de fuego (18–30 daño | cuesta 10 🔵)")
            println("3. 🧪 Curación común (hasta 20 HP | usos: ${player.healingUses}/3)")
            println("4. ✨ Curación mágica (20–30 HP | cuesta 15 🔵)")
        }

        var action: Int
        var validAction: Boolean

        do {
            print("\nEscribe tu opción: ")
            action = readln().toIntOrNull() ?: 0

            validAction = when (player) {
                is Warrior -> when (action) {
                    1 -> true
                    2 -> player.energy >= 2
                    3 -> player.healingUses > 0 &&
                            player.currentHealth < player.maxHealth
                    else -> false
                }

                is Mage -> when (action) {
                    1 -> true
                    2 -> player.mana >= 10
                    3 -> player.healingUses > 0 &&
                            player.currentHealth < player.maxHealth
                    4 -> player.mana >= 15 &&
                            player.currentHealth < player.maxHealth
                    else -> false
                }

                else -> false
            }

            if (!validAction) {
                warning("Acción no disponible. Revisa tus recursos o tu vida.")
            }
        } while (!validAction)

        when (action) {
            1 -> player.attack(monster)

            2 -> {
                if (player is Warrior) {
                    player.powerStrike(monster)
                } else if (player is Mage) {
                    player.castSpell(monster)
                }
            }

            3 -> player.healCommon()

            4 -> {
                if (player is Mage) {
                    player.castHealingSpell()
                }
            }
        }

        if (monster.isAlive && player.isAlive) {
            title("👹 TURNO DEL GOBLIN")
            monster.takeTurn(player)
        }

        if (player.isAlive && monster.isAlive) {
            println("\nPresiona Enter para continuar...")
            readln()
        }
    }

    title("🏆 FIN DEL COMBATE")

    println("\n❤️ ${player.name}: ${player.currentHealth}/${player.maxHealth} HP")
    println("👹 Goblin: ${monster.currentHealth}/${monster.maxHealth} HP")

    if (player.isAlive) {
        success("🎉 ¡Victoria! Has derrotado al Goblin.")
    } else {
        danger("💔 Has sido derrotado. ¡Inténtalo otra vez!")
    }
}
