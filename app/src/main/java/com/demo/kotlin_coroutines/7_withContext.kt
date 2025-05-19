package com.demo.kotlin_coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

// Basically with context is execute task in sequential manner
// With context is not block the thread but its just suspend the function
suspend fun performTaskInWithContext() {
    println("performTaskInWithContext: Before")
    withContext(Dispatchers.IO) {
        delay(1000)
        println("performTaskInWithContext: inside")
    }

    println("performTaskInWithContext: After")
}


suspend fun main() {

    performTaskInWithContext()
}