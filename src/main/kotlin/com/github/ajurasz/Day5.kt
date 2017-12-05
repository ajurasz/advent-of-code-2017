package com.github.ajurasz

object Day5 {

    private fun jump(numbers: List<Int>): Int {
        tailrec fun go(numbers: List<Int>, position: Int = 0, jumps: Int = 1): Int {
            val steps = numbers[position]
            if (position + steps >= numbers.size) return jumps
            return go(
                    numbers.mapIndexed { index, i ->  if (position == index) i + 1 else i },
                    position + steps,
                    jumps.inc())
        }
        return go(numbers)
    }

    @JvmStatic
    fun part1(input: String) = jump(input
            .split("\n")
            .map { it.toInt() })

}