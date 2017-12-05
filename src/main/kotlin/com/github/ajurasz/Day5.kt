package com.github.ajurasz

object Day5 {

    private fun jump(numbers: IntArray, logic: (Int, Int) -> Int): Int {
        tailrec fun go(numbers: IntArray, position: Int = 0, jumps: Int = 1): Int {
            val steps = numbers[position]
            if (position + steps >= numbers.size) return jumps
            return go(
                    numbers.apply { set(position, logic(steps, numbers[position])) },
                    position + steps,
                    jumps.inc())
        }
        return go(numbers)
    }

    @JvmStatic
    fun part1(input: String) = jump(input
            .split("\n")
            .map { it.toInt() }.toIntArray(),
            { _, i -> i + 1 })

    @JvmStatic
    fun part2(input: String) = jump(input
            .split("\n")
            .map { it.toInt() }.toIntArray(),
            { steps, i -> if (steps >= 3) i - 1 else i + 1 })
}