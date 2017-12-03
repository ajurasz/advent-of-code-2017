package com.github.ajurasz

import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.floor
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

    object Part2 {

        interface Action { operator fun not(): Action }
        object Plus: Action { override operator fun not() = Minus }
        object Minus: Action { override operator fun not() = Plus }

        fun Array<IntArray>.sumAdjacent(point: Point): Int {
            return arrayListOf(
                    point.copy(x = point.x + 1),
                    Point(point.x + 1, point.y + 1),
                    point.copy(y = point.y + 1),
                    Point(point.x - 1, y = point.y + 1),
                    point.copy(x = point.x - 1),
                    Point(point.x - 1, point.y - 1),
                    point.copy(y = point.y - 1),
                    Point(point.x + 1, point.y - 1)
            ).fold(0) {acc, p ->
                if (this[p.x][p.y] != 0) acc + this[p.x][p.y] else acc
            }
        }

        private fun init(grid: Array<IntArray>, point: Point) {
            grid[point.x][point.y] = 1
            grid[point.x + 1][point.y] = 1
            grid[point.x + 1][point.y + 1] = 2
        }

        fun fillUntil(grid: Array<IntArray>, until: Int): Int {

            var step = 2
            var action: Action = Minus

            var position = Point(floor(grid.size / 2.toDouble()).toInt())
            init(grid, position)
            position = position.copy(x = position.x + 1, y = position.y + 1)

            fun set(point: Point, value: Int) {
                grid[point.x][point.y] = value
            }

            fun calculate(i: Int): Int = when (action) {
                Minus -> i - 1
                Plus -> i + 1
                else -> throw RuntimeException()
            }

            while(true) {
                (1..step).forEach {
                    position = position.copy(x = calculate(position.x))
                    val sum = grid.sumAdjacent(position)
                    if (sum > until) return sum
                    set(position, sum)
                }
                (1..step).forEach {
                    position = position.copy(y = calculate(position.y))
                    val sum = grid.sumAdjacent(position)
                    if (sum > until) return sum
                    set(position, sum)
                }
                step++
                action = !action
            }
        }
    }

    @JvmStatic
    fun part1(value: Int): Int {
        val square = Part1.Square(value)
        return square.first.distanceTo(square.value())
    }

    @JvmStatic
    fun part2(nextAfter: Int): Int {
        val grid = Array(15) {IntArray(15)}
        return Part2.fillUntil(grid, nextAfter)
    }
}