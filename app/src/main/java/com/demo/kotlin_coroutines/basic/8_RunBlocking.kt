package com.demo.kotlin_coroutines.basic

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {

    launch {
        delay(10000)
        println("Hello")
    }

    println("World")
}