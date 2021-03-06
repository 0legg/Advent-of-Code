package net.olegg.aoc.year2020.day17

import net.olegg.aoc.someday.SomeDay
import net.olegg.aoc.utils.Vector3D
import net.olegg.aoc.year2020.DayOf2020

/**
 * See [Year 2020, Day 17](https://adventofcode.com/2020/day/17)
 */
object Day17 : DayOf2020(17) {
  private val NEIGHBORS_3D =
    (-1..1).flatMap { x ->
      (-1..1).flatMap { y ->
        (-1..1).mapNotNull { z ->
          Vector3D(x, y, z)
        }
      }
    } - Vector3D()

  private val NEIGHBORS_4D =
    (-1..1).flatMap { x ->
      (-1..1).flatMap { y ->
        (-1..1).flatMap { z ->
          (-1..1).mapNotNull { w ->
            Vector4D(x, y, z, w)
          }
        }
      }
    } - Vector4D()

  override fun first(data: String): Any? {
    val items = data
      .trim()
      .lines()
      .map { line -> line.map { it == '#' } }

    val initialState = items
      .flatMapIndexed { y, row ->
        row.mapIndexedNotNull { x, value ->
          if (value) Vector3D(x, y, 0) else null
        }
      }
      .toSet()

    val finalState = (0 until 6).fold(initialState) { state, _ ->
      val neighborCount = state.flatMap { it.neighbors3D() }
        .groupBy { it }
        .mapValues { it.value.size }

      return@fold (state.filter { neighborCount[it] in 2..3 } +
        neighborCount.filter { it.key !in state && it.value == 3 }.map { it.key })
        .toSet()
    }

    return finalState.size
  }

  override fun second(data: String): Any? {
    val items = data
      .trim()
      .lines()
      .map { line -> line.map { it == '#' } }

    val initialState = items
      .flatMapIndexed { y, row ->
        row.mapIndexedNotNull { x, value ->
          if (value) Vector4D(x, y, 0, 0) else null
        }
      }
      .toSet()

    val finalState = (0 until 6).fold(initialState) { state, _ ->
      val neighborCount = state.flatMap { it.neighbors4D() }
        .groupBy { it }
        .mapValues { it.value.size }

      return@fold (state.filter { neighborCount[it] in 2..3 } +
        neighborCount.filter { it.key !in state && it.value == 3 }.map { it.key })
        .toSet()
    }

    return finalState.size
  }

  data class Vector4D(
    var x: Int = 0,
    var y: Int = 0,
    var z: Int = 0,
    var w: Int = 0,
  ) {
    operator fun plus(other: Vector4D) = Vector4D(x + other.x, y + other.y, z + other.z, w + other.w)
  }

  private fun Vector3D.neighbors3D(): List<Vector3D> {
    return NEIGHBORS_3D.map { this + it }
  }

  private fun Vector4D.neighbors4D(): List<Vector4D> {
    return NEIGHBORS_4D.map { this + it }
  }
}

fun main() = SomeDay.mainify(Day17)
