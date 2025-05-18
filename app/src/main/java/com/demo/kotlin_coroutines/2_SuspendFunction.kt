package com.demo.kotlin_coroutines

import android.util.Log
import kotlinx.coroutines.yield

const val TAG_SUSPENDING_FUNCTION = "TAG_SUSPENDING_FUNCTION"

suspend fun task1() {
    Log.e(TAG_SUSPENDING_FUNCTION, "task1: started")
    yield()
    Log.e(TAG_SUSPENDING_FUNCTION, "task1: ended")
}

suspend fun task2() {
    Log.e(TAG_SUSPENDING_FUNCTION, "task2: started")
    yield()
    Log.e(TAG_SUSPENDING_FUNCTION, "task2: ended")
}