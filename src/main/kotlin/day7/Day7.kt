package day7

import someday.SomeDay

/**
 * Created by olegg on 12/19/15.
 */
class Day7: SomeDay(7) {
    val source = data.lines().map {
            val matcher = "^(.*) -> (.*)$".toPattern().matcher(it)
            matcher.find()
            matcher.group(2) to matcher.group(1)
        }.toMap()
    val notPattern = "^NOT (\\d+)$".toPattern()
    val andPattern = "^(\\d+) AND (\\d+)$".toPattern()
    val orPattern = "^(\\d+) OR (\\d+)$".toPattern()
    val lshiftPattern = "^(\\d+) LSHIFT (\\d+)$".toPattern()
    val rshiftPattern = "^(\\d+) RSHIFT (\\d+)$".toPattern()
    var varRegex = "[a-z]".toRegex()

    fun measure(board: Map<String, String>, pin: String): String {
        var state = board
        var resolved = mapOf<String, String>().toLinkedMap()
        while (!resolved.contains(pin)) {
            val temp = state.filterValues { !it.contains(varRegex) }.mapValues {
                val notMatcher = notPattern.matcher(it.value)
                val andMatcher = andPattern.matcher(it.value)
                val orMatcher = orPattern.matcher(it.value)
                val lshiftMatcher = lshiftPattern.matcher(it.value)
                val rshiftMatcher = rshiftPattern.matcher(it.value)
                (0xffff and when {
                    notMatcher.matches() -> notMatcher.group(1).toInt().inv()
                    andMatcher.matches() -> andMatcher.group(1).toInt() and andMatcher.group(2).toInt()
                    orMatcher.matches() -> orMatcher.group(1).toInt() or orMatcher.group(2).toInt()
                    lshiftMatcher.matches() -> lshiftMatcher.group(1).toInt() shl lshiftMatcher.group(2).toInt()
                    rshiftMatcher.matches() -> rshiftMatcher.group(1).toInt() shr rshiftMatcher.group(2).toInt()
                    else -> it.value.toInt()
                }).toString()
            }

            state = state.filterKeys { !temp.containsKey(it) }
                    .mapValues { temp.toList().fold(it.value) { acc, value ->
                        acc.replace("\\b${value.first}\\b".toRegex(), value.second)
                    } }
            resolved.putAll(temp)
        }
        return resolved[pin] ?: ""
    }

    override fun first(): String {
        return measure(source, "a")
    }

    override fun second(): String {
        return measure(source + ("b" to measure(source, "a")), "a")
    }
}

fun main(args: Array<String>) {
    val day = Day7()
    println(day.first())
    println(day.second())
}