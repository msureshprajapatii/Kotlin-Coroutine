package com.demo.kotlin_coroutines

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

val TAG = "AppCompatActivity"

fun executeTask(){

    // Just Print Thread Name
    Log.d(TAG, "executeTask: Current Thread = ${Thread.currentThread().name}")

    for (i in 1..100000000000L){

    }
}

fun executeTaskWithThread(){
    thread {

        // Just Print Thread Name
        Log.d(TAG, "executeTaskWithThread: Current Thread = ${Thread.currentThread().name}")

        for (i in 1..100000000000L){

        }
    }
}


fun executeTaskWithCoroutine(){
    CoroutineScope(Dispatchers.IO).launch {

        // Just Print Thread Name
        Log.d(TAG, "executeTaskWithCoroutine: Current Thread = ${Thread.currentThread().name}")

        for (i in 1..10000L){

        }
    }
}