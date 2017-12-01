package com.github.ajurasz

class CyclicList<out T>(private val delegate: List<T>) : AbstractList<T>() {
    override val size: Int get() = delegate.size
    override fun get(index: Int): T = delegate[if (index >= this.size) index - this.size else index]
}

object Day1 {

    private fun convert(input: String) = CyclicList(input.map(Char::toIntValue))

    private fun sum(digits: List<Int>, distance: (Int) -> Int): Int {
        val dist = distance(digits.size)
        return digits.foldIndexed(0) { index, acc, value ->
            when (value) {
                digits[dist + index] -> acc + value
                else -> acc
            }
        }
    }

    @JvmStatic
    fun captchaV1(input: String) = sum(convert(input)) { 1 }

    @JvmStatic
    fun captchaV2(input: String) = sum(convert(input)) { it / 2 }
}