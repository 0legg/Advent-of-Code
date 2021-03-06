package net.olegg.aoc.year2019.day3

import net.olegg.aoc.someday.SomeDay
import net.olegg.aoc.utils.Directions
import net.olegg.aoc.utils.Vector2D
import net.olegg.aoc.year2019.DayOf2019
import kotlin.math.abs

/**
 * See [Year 2019, Day 3](https://adventofcode.com/2019/day/3)
 */
object Day3 : DayOf2019(3) {
  override fun first(data: String): Any? {
    val (wire1, wire2) = data
      .trim()
      .lines()
      .map { line -> line.split(",").map { Directions.valueOf(it.substring(0, 1)) to it.substring(1).toInt() } }
      .map { wire ->
        wire.fold(listOf(Vector2D())) { acc, (dir, length) ->
          val start = acc.last()
          return@fold acc + (1..length).map { start + dir.step * it }
        }
      }
      .map { it.toSet() - Vector2D() }

    return (wire1.intersect(wire2)).map { abs(it.x) + abs(it.y) }.minOrNull()
  }

  override fun second(data: String): Any? {
    val (wire1, wire2) = data
      .trim()
      .lines()
      .map { line -> line.split(",").map { Directions.valueOf(it.substring(0, 1)) to it.substring(1).toInt() } }
      .map { wire ->
        wire.fold(listOf(Vector2D() to 0)) { acc, (dir, length) ->
          val start = acc.last()
          return@fold acc + (1..length).map { start.first + dir.step * it to start.second + it }
        }
      }
      .map { it.drop(1) }
      .map { wire ->
        wire
          .groupBy(keySelector = { it.first }, valueTransform = { it.second })
          .mapValues { (_, points) -> points.minOrNull() ?: 1_000_000 }
      }

    return wire1.mapValues { (point, distance) -> distance + (wire2[point] ?: 1_000_000) }
      .minByOrNull { it.value }
      ?.value
  }
}

fun main() = SomeDay.mainify(Day3)
