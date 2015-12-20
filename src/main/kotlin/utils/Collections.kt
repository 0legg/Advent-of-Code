package utils

import java.util.*

/**
 * Created by olegg on 12/18/15.
 */

/**
 * Accumulates value starting with [initial] value and applying [operation] from left to right to current accumulator value and each element.
 */
public inline fun <T, R> Iterable<T>.scan(initial: R, operation: (R, T) -> R): List<R> {
    var accumulator = initial
    var list = listOf<R>()
    for (element in this) {
        accumulator = operation(accumulator, element)
        list += accumulator
    }
    return list
}

/**
 * Accumulates value starting with [initial] value and applying [operation] from left to right to current accumulator value and each element.
 */
public inline fun <T, R> Sequence<T>.scan(initial: R, operation: (R, T) -> R): List<R> {
    var accumulator = initial
    var list = listOf<R>()
    for (element in this) {
        accumulator = operation(accumulator, element)
        list += accumulator
    }
    return list
}

public fun <T : Any> List<T>.permutations() : Sequence<List<T>> = if (size == 1) sequenceOf(this) else {
    val iterator = iterator()
    var head = iterator.next()
    var permutations = (this - head).permutations().iterator()

    fun nextPermutation(): List<T>? = if (permutations.hasNext()) listOf(head) + permutations.next() else {
        if (iterator.hasNext()) {
            head = iterator.next()
            permutations = (this - head).permutations().iterator()
            nextPermutation()
        } else null
    }

    sequence { nextPermutation() }
}

public fun <T> Iterable<T>.series(): List<List<T>> {
    var list = arrayListOf<ArrayList<T>>()
    var store = arrayListOf<T>()
    for (element in this) {
        if (store.contains(element)) {
            store.add(element)
        } else {
            store = arrayListOf(element)
            list.add(store)
        }
    }

    return list
}