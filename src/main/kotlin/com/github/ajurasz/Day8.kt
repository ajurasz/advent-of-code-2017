package com.github.ajurasz

import java.util.regex.Pattern

object Day8 {

    interface Action
    object Inc : Action
    object Dec : Action

    object ActionBuilder {
        fun create(action: String): Action = when (action.toLowerCase()) {
            "inc" -> Inc
            "dec" -> Dec
            else -> throw IllegalStateException("Unknown $action")
        }
    }

    interface Condition
    object GT : Condition
    object LT : Condition
    object GTE : Condition
    object LTE : Condition
    object NOT : Condition
    object EQ : Condition

    object ConditionBuilder {
        fun create(condition: String): Condition = when (condition.toLowerCase()) {
            ">" -> GT
            "<" -> LT
            ">=" -> GTE
            "<=" -> LTE
            "!=" -> NOT
            "==" -> EQ
            else -> throw IllegalStateException("Unknown $condition")
        }
    }

    private val patter = Pattern.compile("""(\w+)? (inc|dec) (-?\d+) if (\w+) (>|<|>=|<=|!=|==) (-?\d+)""")

    private data class Instruction(val variable: String, val action: Action, val value: Int,
                                   val conditionVariable: String, val condition: Condition, val conditionValue: Int)

    @JvmStatic
    fun part1(input: String): Int {
        val registry = mutableMapOf<String, Int>()

        fun execute(instruction: Instruction) {
            val x = registry.getOrDefault(instruction.conditionVariable, 0)
            val condition = when (instruction.condition) {
                GT -> x > instruction.conditionValue
                LT -> x < instruction.conditionValue
                GTE -> x >= instruction.conditionValue
                LTE -> x <= instruction.conditionValue
                NOT -> x != instruction.conditionValue
                EQ -> x == instruction.conditionValue
                else -> false
            }
            if (condition) {
                val v = registry.getOrDefault(instruction.variable, 0)
                when (instruction.action) {
                    Inc -> registry.put(instruction.variable, v + instruction.value)
                    Dec -> registry.put(instruction.variable, v - instruction.value)
                }
            }
        }

        input.split("\n")
                .map {
                    val matcher = patter.matcher(it)
                    matcher.find()
                    with(matcher) {
                      Instruction(group(1), ActionBuilder.create(group(2)), group(3).toInt(),
                              group(4), ConditionBuilder.create(group(5)), group(6).toInt())
                    }
                }.forEach {
                    execute(it)
                }

        return registry.values.max()!!
    }

    @JvmStatic
    fun part2   (input: String): Int {
        var max = 0
        val registry = mutableMapOf<String, Int>()

        fun execute(instruction: Instruction) {
            val x = registry.getOrDefault(instruction.conditionVariable, 0)
            val condition = when (instruction.condition) {
                GT -> x > instruction.conditionValue
                LT -> x < instruction.conditionValue
                GTE -> x >= instruction.conditionValue
                LTE -> x <= instruction.conditionValue
                NOT -> x != instruction.conditionValue
                EQ -> x == instruction.conditionValue
                else -> false
            }
            if (condition) {
                val v = registry.getOrDefault(instruction.variable, 0)
                when (instruction.action) {
                    Inc -> registry.put(instruction.variable, v + instruction.value)
                    Dec -> registry.put(instruction.variable, v - instruction.value)
                }
            }
        }

        input.split("\n")
                .map {
                    val matcher = patter.matcher(it)
                    matcher.find()
                    with(matcher) {
                        Instruction(group(1), ActionBuilder.create(group(2)), group(3).toInt(),
                                group(4), ConditionBuilder.create(group(5)), group(6).toInt())
                    }
                }.forEach {
                    execute(it)
                    if (registry.isNotEmpty() && registry.values.max()!! > max) {
                        max = registry.values.max()!!
                    }
                }

        return max
    }
}