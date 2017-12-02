package com.github.ajurasz

object Day2 {

    private fun checksum(input: String, op: (List<Int>) -> Int?) = input
            .split("\n")
            .map { it.split("\t").map { it.toInt() } }
            .filter { it.isNotEmpty() }
            .mapNotNull { op(it) }
            .sum()

    @JvmStatic
    fun checksumV1(input: String) = checksum(input) { it.max()!! - it.min()!! }

    @JvmStatic
    fun checksumV2(input: String) = checksum(input) {
        val withIndex = it.withIndex()
        for ((index, value) in withIndex) {
            for (number in it.subList(index + 1, it.size)) {
                when {
                    value % number == 0 -> return@checksum value / number
                    number % value == 0 -> return@checksum number / value
                }
            }
        }
        null
    }
}