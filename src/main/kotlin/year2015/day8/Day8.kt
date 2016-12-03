package year2015.day8

import year2015.DayOf2015

/**
 * Created by olegg on 12/19/15.
 */
class Day8 : DayOf2015(8) {
    val strings = data.lines()
    override fun first(): String {
        return (strings.sumBy { it.length } - strings.sumBy {
            it
                    .replace("^\"".toRegex(), "")
                    .replace("\"$".toRegex(), "")
                    .replace("\\\"", "\"")
                    .replace("\\\\", "\\")
                    .replace("\\\\x[0-9a-f]{2}".toRegex(), "#")
                    .length
        }).toString()
    }

    override fun second(): String {
        return (strings.sumBy {
            it.map {
                when (it) {
                    '\"' -> "\\\""
                    '\\' -> "\\\\"
                    else -> "$it"
                }
            }.joinToString(prefix = "\"", postfix = "\"", separator = "").length
        } - strings.sumBy { it.length }).toString()
    }
}

fun main(args: Array<String>) {
    val day = Day8()
    println(day.first())
    println(day.second())
}
