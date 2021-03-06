package net.olegg.aoc.year2016.day18

import net.olegg.aoc.someday.SomeDay
import net.olegg.aoc.year2016.DayOf2016

/**
 * See [Year 2016, Day 18](https://adventofcode.com/2016/day/18)
 */
object Day18 : DayOf2016(18) {

  val patterns = listOf(
    "\\^\\^\\.".toRegex(),
    "\\.\\^\\^".toRegex(),
    "\\^\\.\\.".toRegex(),
    "\\.\\.\\^".toRegex()
  )

  override fun first(data: String): Any? {
    return solve(data, 40)
  }

  override fun second(data: String): Any? {
    return solve(data, 400000)
  }

  fun solve(data: String, rows: Int): Int {
    return (1 until rows).fold(".$data." to data.count { it == '.' }) { acc, _ ->
      val traps = patterns.flatMap { it.findAll(acc.first).map { it.range.start + 1 }.toList() }
      val row = acc.first.indices.map { if (traps.contains(it)) '^' else '.' }.joinToString(separator = "")
      return@fold row to acc.second + row.count { it == '.' } - 2
    }.second
  }
}

fun main() = SomeDay.mainify(Day18)
