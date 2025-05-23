package com.demo.kotlin_coroutines.trickyprograms

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    println("Outer start")
    launch {
        println("Inner start")
        delay(500)
        println("Inner end")
    }.join()
    println("Outer end")
}

/*

Output
------
Outer start
Inner start
Inner end
Outer end

Process finished with exit code 0

-> Here launch block is create another coroutine but we use .join() also so it will wait
   until launch will not complete their task. that's why its executed sequentially

-> if we remove .join() then it will execute in non blocking manner and output get like below

Output
-------
Outer start
Outer end
Inner start
Inner end

Process finished with exit code 0


*/