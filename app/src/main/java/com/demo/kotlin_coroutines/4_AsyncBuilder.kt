package com.demo.kotlin_coroutines

import android.util.Log
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay


// Async Builder
// async.await() is help us to suspend coroutine until task is not complete
// Basically its execute task in sequentially manner when we use async await
// Benefit : Code concise compare to launch
suspend fun asyncCoroutineBuilder() {
    val job = CoroutineScope(Dispatchers.IO).async {
        getFaceBookFollowers()
    }
    Log.e(TAG, "fbFollowers with async and await: ${job.await()}")
}


private suspend fun getFaceBookFollowers(): Int {
    delay(1000)
    return 45
}