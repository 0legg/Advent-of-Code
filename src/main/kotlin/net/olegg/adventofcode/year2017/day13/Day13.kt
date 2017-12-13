package net.olegg.adventofcode.year2017.day13

import net.olegg.adventofcode.someday.SomeDay
import net.olegg.adventofcode.year2017.DayOf2017

/**
 * @see <a href="http://adventofcode.com/2017/day/13">Year 2017, Day 13</a>
 */
class Day13 : DayOf2017(13) {
    override fun first(data: String): String {
        return data.trimIndent()
                .lines()
                .map { it.split(": ").map { it.toInt() }.let { it[0] to it[1] } }
                .filter { it.first % ((it.second - 1) * 2) == 0 }
                .map { it.first * it.second }
                .sum()
                .toString()
    }

    override fun second(data: String): String {
        val filters = data.trimIndent()
                .lines()
                .map { it.split(": ").map { it.toInt() }.let { it[0] to it[1] } }

        return generateSequence(0) { it + 1 }
                .first { delay ->
                    filters.none { (it.first + delay) % ((it.second - 1) * 2) == 0 }
                }
                .toString()
    }
}

fun main(args: Array<String>) = SomeDay.mainify(Day13::class)
