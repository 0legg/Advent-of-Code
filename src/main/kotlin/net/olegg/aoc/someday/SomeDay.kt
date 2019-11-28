package net.olegg.aoc.someday

import kotlin.system.measureTimeMillis

/**
 * Abstract class representing solution for [day]s problem in specified [year].
 */
abstract class SomeDay(val year: Int, val day: Int) {
  val data: String = Fetcher.fetcher.fetchInput(year, day).execute().body().orEmpty()

  open fun first(data: String): Any? {
    return null
  }

  open fun second(data: String): Any? {
    return null
  }

  companion object {
    fun mainify(someday: SomeDay) {
      with(someday) {
        println("Year $year, day $day")
        measureTimeMillis {
          println("First: ${first(data)?.toString() ?: "unsolved"}")
        }.run {
          println("Time: ${this}ms")
        }
        measureTimeMillis {
          println("Second: ${second(data)?.toString() ?: "unsolved"}")
        }.run {
          println("Time: ${this}ms")
        }
      }
    }
  }
}