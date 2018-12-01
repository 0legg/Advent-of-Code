package net.olegg.adventofcode.year2018.day1

import net.olegg.adventofcode.someday.SomeDay
import net.olegg.adventofcode.year2018.DayOf2018

/**
 * @see <a href="http://adventofcode.com/2018/day/1">Year 2018, Day 1</a>
 */
class Day1 : DayOf2018(1) {
    override fun first(data: String): String {
        return data
                .trim()
                .lines()
                .map { it.removePrefix("+") }
                .map { it.toInt() }
                .sum()
                .toString()
    }
}

fun main(args: Array<String>) = SomeDay.mainify(Day1::class)
