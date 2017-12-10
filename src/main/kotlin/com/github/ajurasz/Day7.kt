package com.github.ajurasz

import java.util.regex.Pattern

object Day7 {

    private val pattern: Pattern = Pattern.compile("""(\w+ )\((\d+)\)( -> )?(.*)?""")

    data class Program(val name: String, val weight: Int, val disc: List<String>)

    @JvmStatic
    fun part1(input: String) = input
            .split("\n")
            .map {
                val matcher = pattern.matcher(it)
                matcher.find()
                Program(
                        matcher.group(1).trim(),
                        matcher.group(2).toInt(),
                        matcher?.group(4)?.split(",")?.map { it.trim() } ?: emptyList()
                )
            }
            .fold(Pair<Set<String>, Set<String>>(setOf(), setOf())) { acc, program ->
                Pair(acc.first.plus(program.name), acc.second.plus(program.disc.map { it }))
            }.run { first - second }.first()
}