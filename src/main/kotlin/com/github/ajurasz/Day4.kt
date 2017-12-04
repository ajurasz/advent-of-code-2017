package com.github.ajurasz

object Day4 {

    private fun String.sort() = this.split("")
            .sorted()
            .joinToString()

    private fun isValidPassphrase(s: String, grouping: (String) -> String) =
            s.split(" ").groupingBy(grouping).eachCount().filterValues { it > 1 }.isEmpty()

    private fun isValidPart1(s: String) = isValidPassphrase(s) { it }

    private fun isValidPart2(s: String) = isValidPassphrase(s) { it.sort() }

    @JvmStatic
    fun part1(input: String) = input.split("\n").count(this::isValidPart1)

    @JvmStatic
    fun part2(input: String) = input.split("\n").count(this::isValidPart2)
}