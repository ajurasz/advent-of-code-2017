package com.github.ajurasz

fun <T> List<T>.circular() = this + this.first()