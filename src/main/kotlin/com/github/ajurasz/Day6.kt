package com.github.ajurasz

object Day6 {

    private fun IntArray.toKey() = this.joinToString()

    private fun redistribution(input: IntArray, repeats: Int = 1): Int {
        tailrec fun go(saw: Set<String>, banks: IntArray, counter: Int = 1, times: Int = 1): Int {
            val index = banks.indices.maxBy { banks[it] } ?: -1
            val value = banks[index]
            banks[index] = 0
            (1..value).forEach {
                banks[(index + it) % banks.size] = banks[(index + it) % banks.size] + 1
            }
            if (saw.contains(banks.toKey()) && repeats == times)
                return counter
            else if (saw.contains(banks.toKey()) && repeats > times)
                return go(setOf(banks.toKey()), banks, 1, times + 1)
            return go(saw.plus(banks.toKey()), banks, counter + 1, times)
        }

        return go(emptySet(), input)
    }

    @JvmStatic
    fun part1(input: String) = redistribution(input.split("\t").map { it.toInt() }.toIntArray())

    @JvmStatic
    fun part2(input: String) = redistribution(input.split("\t").map { it.toInt() }.toIntArray(), 2)
}