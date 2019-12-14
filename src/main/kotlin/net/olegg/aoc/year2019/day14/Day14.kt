package net.olegg.aoc.year2019.day14

import net.olegg.aoc.someday.SomeDay
import net.olegg.aoc.utils.Vector3D
import net.olegg.aoc.year2019.DayOf2019
import net.olegg.aoc.year2019.day12.Day12
import java.util.ArrayDeque

/**
 * See [Year 2019, Day 14](https://adventofcode.com/2019/day/14)
 */
object Day14 : DayOf2019(14) {
  override fun first(data: String): Any? {
    val forms = data
        .trim()
        .lines()
        .map { line ->
          val raw = line.replace(" => ", ", ")
            .split(", ")
            .map { chunk -> chunk.split(" ").let { it.last() to it.first().toInt() } }
          return@map raw.last().first to (raw.last().second to raw.dropLast(1))
        }

    val nodes = forms.flatMap { (from, toList) ->  toList.second.map { it.first } + from }.toSet()
    val edges = forms.flatMap { (from, toList) -> toList.second.map { from to it.first } }.toMutableSet()
    val available = nodes.toMutableSet()
    val empty = ArrayDeque((available - edges.map { it.second }).toSet())
    available.removeAll(empty)
    val dag = ArrayDeque<String>()
    while (empty.isNotEmpty()) {
      val curr = empty.pop()
      dag.offer(curr)
      edges.removeIf { it.first == curr }
      val newEmpty = (available - edges.map { it.second }).toSet()
      empty.addAll(newEmpty)
      available.removeAll(newEmpty)
    }

    val stock = mutableMapOf("FUEL" to 1)
    for (item in dag) {
      val form = forms.firstOrNull { it.first == item }
      if (form != null) {
        val need = stock.getOrDefault(item, 0)
        if (need > 0) {
          val quantity = ((need - 1) / form.second.first) + 1
          form.second.second.forEach { (element, required) ->
            stock[element] = stock.getOrDefault(element, 0) + required * quantity
          }
        }
      }
    }

    return stock["ORE"]
  }
}

fun main() = SomeDay.mainify(Day14)
