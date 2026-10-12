
object Colors {
    const val RESET = "\u001B[0m"
    const val RED = "\u001B[31m"
    const val GREEN = "\u001B[32m"
    const val YELLOW = "\u001B[33m"
    const val BLUE = "\u001B[34m"
    const val MAGENTA = "\u001B[35m"
    const val CYAN = "\u001B[36m"
    const val BOLD = "\u001B[1m"
    const val PURPLE_BG = "\u001B[48;5;57m"
    const val WHITE = "\u001B[97m"
}

fun title(text: String) {
    println()
    println("${Colors.PURPLE_BG}${Colors.WHITE}${Colors.BOLD} ✦ $text ✦ ${Colors.RESET}")
}

fun success(text: String) {
    println("${Colors.GREEN}$text${Colors.RESET}")
}

fun warning(text: String) {
    println("${Colors.YELLOW}$text${Colors.RESET}")
}

fun danger(text: String) {
    println("${Colors.RED}$text${Colors.RESET}")
}

fun magic(text: String) {
    println("${Colors.BLUE}$text${Colors.RESET}")
}

