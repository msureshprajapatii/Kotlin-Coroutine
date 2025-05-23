package com.demo.kotlin_coroutines.trickyprograms

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    launch {
        delay(1000)
        println("World!")
    }
    println("Hello,")
}

/*

Output
------
Hello,
World!

Process finished with exit code 0


-> because inside runBlocking, launch coroutine builder execute concurrently and non blocking manner



* */
