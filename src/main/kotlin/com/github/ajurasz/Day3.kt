package com.github.ajurasz

import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.sqrt

object Day3 {

    data class Point(val x: Int, val y: Int = x) {
        fun distanceTo(point: Point) = abs(point.x - this.x) + abs(point.y - this.y)
    }

    object Part1 {
        interface Type
        object Even : Type
        object Odd : Type

        data class Square(private val value: Int) {
            private val size = ceil(sqrt(value.toDouble())).toInt()
            private val type: Type = if (size % 2 == 0) Even else Odd
            private val lastValue = size * size

            private fun half(): Int = ceil(size.toDouble() / 2).toInt() - 1

            val first = Point(half(), half())

            val last = when (type) {
                Even -> Point(0, size - 1)
                Odd -> Point(size - 1, 0)
                else -> throw RuntimeException("ups")
            }

            private fun valueForEven(diff: Int): Point {
                return when {
                    diff < size -> last.copy(x = diff)
                    diff >= size -> Point(x = size - 1, y = abs((size - 1) * 2 - diff))
                    else -> throw RuntimeException("ups")
                }
            }

            private fun valueForOdd(diff: Int): Point {
                return when {
                    diff <= size -> last.copy(x = last.x - diff)
                    diff > size -> Point(x = 0, y = abs(size - diff - 1))
                    else -> throw RuntimeException("ups")
                }
            }

            fun value(): Point {
                val diff = lastValue - value
                return when (type) {
                    Even -> valueForEven(diff)
                    Odd -> valueForOdd(diff)
                    else -> throw RuntimeException("ups")
                }
            }
        }
    }

    @JvmStatic
    fun part1(value: Int): Int {
        val square = Part1.Square(value)
        return square.first.distanceTo(square.value())
    }

    @JvmStatic
    fun part2(value: Int): Int {
        val grid = Array(7) {IntArray(7)}
        return 1
    }
}