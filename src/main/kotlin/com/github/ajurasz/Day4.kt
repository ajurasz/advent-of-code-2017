package com.github.ajurasz

object Day4 {

    private fun String.sort() = this.split("")
            .sorted()
            .joinToString()

    private fun isValidPassphrase(text: String, transform: (String) -> String) = text
            .split(" ")
            .map(transform)
            .groupingBy { it }
            .eachCount()
            .values
            .filter { it > 1 }
            .count() == 0

    @JvmStatic
    fun part1(input: String) = input
            .split("\n")
            .filter{ isValidPassphrase(it) { it } }
            .count()

    @JvmStatic
    fun part2(input: String) = input
            .split("\n")
            .filter{ isValidPassphrase(it) { it.sort() } }
            .count()
}