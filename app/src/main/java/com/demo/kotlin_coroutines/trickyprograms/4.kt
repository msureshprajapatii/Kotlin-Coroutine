package com.demo.kotlin_coroutines.trickyprograms

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() {
    println("Before runBlocking")
    runBlocking {
        delay(2000)
        println("Inside runBlocking")
    }
    println("After runBlocking")
}

/*

Output
------
Before runBlocking
Inside runBlocking   ← (after ~2 seconds delay)
After runBlocking

Process finished with exit code 0

-> Here runBlocking block the thread for 2 second until task not completed,
   then after return and print next line

*/