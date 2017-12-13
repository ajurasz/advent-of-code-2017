package com.github.ajurasz

import java.util.regex.Pattern

object Day8 {

    val actions = mapOf<String, (Int, Int) -> Int>(
            "inc" to { x, y -> x + y},
            "dec" to { x, y -> x - y}
    )

    val conditions = mapOf<String, (Int, Int) -> Boolean>(
            ">" to { x, y -> x > y},
            ">=" to { x, y -> x >= y},
            "<" to { x, y -> x < y},
            "<=" to { x, y -> x <= y},
            "!=" to { x, y -> x != y},
            "==" to { x, y -> x == y}
    )

    private val patter = Pattern.compile("""(\w+)? (inc|dec) (-?\d+) if (\w+) (>|<|>=|<=|!=|==) (-?\d+)""")

    private fun eval(input: String, max: Boolean): Int {
        val registry = mutableMapOf<String, Int>(
                "MAX" to 0
        )
        input.split("\n")
                .forEach {
                    val matcher = patter.matcher(it)
                    matcher.find()
                    with(matcher) {
                        if (conditions[group(5)]?.invoke(registry.getOrDefault(group(4), 0), group(6).toInt()) == true) {
                            val x = actions[group(2)]?.invoke(registry.getOrDefault(group(1), 0), group(3).toInt()) ?: throw IllegalStateException()
                            registry.put(group(1), x)
                            registry.put("MAX", registry.computeIfPresent("MAX", { _, oldValue -> if (x > oldValue) x else oldValue }) ?: throw IllegalStateException())
                        }
                    }
                }
        return registry.filterKeys { if(max) it == "MAX" else it != "MAX" }.values.max()!!
    }

    @JvmStatic
    fun part1(input: String) = eval(input, false)

    @JvmStatic
    fun part2(input: String) = eval(input, true)

}