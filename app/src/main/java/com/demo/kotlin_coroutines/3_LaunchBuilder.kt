package com.demo.kotlin_coroutines

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


private suspend fun getFaceBookFollowers(): Int {
    delay(1000)
    return 45
}


// its give result as zero(0) because its not wait until coroutine task are complete
//  its execute task in parallel manner
suspend fun launchWithoutJoin() {
    var fbFollowers = 0
    val job = CoroutineScope(Dispatchers.IO).launch {
        fbFollowers = getFaceBookFollowers()
    }
    Log.e(TAG, "fbFollowers with launch without join : $fbFollowers")

}

// Launch Builder
// job.Join() is help us to suspend coroutine until task is not complete
// basically its execute task in sequentially manner
suspend fun launchCoroutineBuilder() {
    var fbFollowers = 0
    val job = CoroutineScope(Dispatchers.IO).launch {
        fbFollowers = getFaceBookFollowers()
    }
    job.join()
    Log.e(TAG, "fbFollowers with launch and join : $fbFollowers")
}