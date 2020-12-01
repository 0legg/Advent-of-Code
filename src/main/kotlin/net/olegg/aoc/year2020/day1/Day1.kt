package net.olegg.aoc.year2020.day1

import net.olegg.aoc.someday.SomeDay
import net.olegg.aoc.utils.parseLongs
import net.olegg.aoc.year2020.DayOf2020

/**
 * See [Year 2020, Day 1](https://adventofcode.com/2020/day/1)
 */
object Day1 : DayOf2020(1) {
  override fun first(data: String): Any? {
    val nums = data
        .parseLongs("\n")
        .toSet()
    val first = nums.first { (2020L - it) in nums }
    return first * (2020L - first)
  }
}

fun main() = SomeDay.mainify(Day1)
