package com.demo.kotlin_coroutines.trickyprograms

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    println("Outer start")
    runBlocking {
        println("Inner start")
        delay(500)
        println("Inner end")
    }
    println("Outer end")
}

/*

Output
--------------------------
Outer start
Inner start
Inner end
Outer end

Process finished with exit code 0

-> because nested runBlocking again block the current thread and execute task sequentially


*/