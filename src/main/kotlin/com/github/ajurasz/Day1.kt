package com.github.ajurasz

object Day1 {

    private fun convert(input: String) = input.map(Char::toIntValue)

    private fun sum(distance: Int, digits: List<Int>): Int {
        val referenceList = digits + digits
        return digits.foldIndexed(0) { index, acc, value ->
            when (value) {
                referenceList[distance + index] -> acc + value
                else -> acc
            }
        }
    }

    @JvmStatic
    fun captchaV1(input: String) = sum(1, convert(input))

    @JvmStatic
    fun captchaV2(input: String): Int {
        val digits = convert(input)
        return sum(digits.size / 2, convert(input))
    }
}