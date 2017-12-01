package com.github.ajurasz

class Acc(val prev: Int, val sum: Int)

object Day1 {

    @JvmStatic
    fun captchaV1(input: String) = input
            .map { it - '0' }
            .circular()
            .foldIndexed(Acc(0, 0)) { idx, acc, value ->
                when {
                    idx == 0 -> Acc(value, 0)
                    acc.prev == value -> Acc(value, acc.sum + value)
                    else -> Acc(value, acc.sum)
                }
            }
            .sum

    @JvmStatic
    fun captchaV2(input: String): Int {
        val digits = input.map { it - '0' }
        fun get(digits: List<Int>, idx: Int): Int {
            val len = digits.size
            val step = len / 2
            val newIdx = idx + step
            return when {
                newIdx < len -> digits[idx+step]
                newIdx >= len -> digits[newIdx - len]
                else -> throw RuntimeException()
            }
        }

        return digits.foldIndexed(0) { idx, acc, value ->
            when {
                value == get(digits, idx) -> acc + value
                else -> acc
            }
        }
    }
}