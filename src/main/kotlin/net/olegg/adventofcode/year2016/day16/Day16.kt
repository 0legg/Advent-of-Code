package net.olegg.adventofcode.year2016.day16

import net.olegg.adventofcode.someday.SomeDay
import net.olegg.adventofcode.utils.chunks
import net.olegg.adventofcode.year2016.DayOf2016

/**
 * @see <a href="http://adventofcode.com/2016/day/16">Year 2016, Day 16</a>
 */
class Day16 : DayOf2016(16) {
    override fun first(data: String): String {
        return checksum(data, 272)
    }

    override fun second(data: String): String {
        return checksum(data, 35651584)
    }

    fun checksum(initial: String, length: Int): String {
        val curve = generateSequence(initial) {
            it + "0" + it.reversed().replace('0', '2').replace('1', '0').replace('2', '1')
        }
                .dropWhile { it.length <= length }
                .first()
                .substring(0, length)

        return generateSequence(curve) {
            it.asSequence()
                    .chunks(2)
                    .filter { it.isNotEmpty() }
                    .joinTo(StringBuilder(), separator = "") { if (it[0] == it[1]) "1" else "0" }
                    .toString()
        }
                .first { it.length % 2 == 1 }
    }
}

fun main(args: Array<String>) = SomeDay.mainify(Day16::class)
