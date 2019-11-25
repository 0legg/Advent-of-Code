package net.olegg.adventofcode.year2015.day4

import net.olegg.adventofcode.someday.SomeDay
import net.olegg.adventofcode.utils.md5
import net.olegg.adventofcode.year2015.DayOf2015

/**
 * See [Year 2015, Day 4](https://adventofcode.com/2015/day/4)
 */
class Day4 : DayOf2015(4) {
  override fun first(data: String): Any? {
    return generateSequence(1) { it + 1 }.first { (data + it.toString()).md5().startsWith("00000") }
  }

  override fun second(data: String): Any? {
    return generateSequence(1) { it + 1 }.first { (data + it.toString()).md5().startsWith("000000") }
  }
}

fun main() = SomeDay.mainify(Day4::class)