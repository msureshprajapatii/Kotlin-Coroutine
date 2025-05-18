package com.demo.kotlin_coroutines

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun printFollowersWithLaunch(){
    var fbFollowers = 0
    var instaFollowers = 0
    var tweeterFollowers = 0

    val job1 = CoroutineScope(Dispatchers.IO).launch {
        fbFollowers = getFaceBookFollowers()
    }

    val job2 = CoroutineScope(Dispatchers.IO).launch {
        tweeterFollowers = getTweeterFollowers()
    }

    val job3 = CoroutineScope(Dispatchers.IO).launch {
        instaFollowers = getInstagramFollowers()
    }

    job1.join()
    job2.join()
    job3.join()
    Log.d(TAG, "printFollowersWithLaunch: job1 = $fbFollowers")
    Log.d(TAG, "printFollowersWithLaunch: job2 = $tweeterFollowers")
    Log.d(TAG, "printFollowersWithLaunch: job3 = $instaFollowers")

}

// When you uncomment throws Exception you can check what will happen
suspend fun getTweeterFollowers(): Int {
    delay(500)
   // throw Exception("Failed")
    return 55
}


// Get Facebook followers
private suspend fun getFaceBookFollowers(): Int {
    delay(1000)
    return 45
}

// Get Instagram followers
private suspend fun getInstagramFollowers(): Int {
    delay(1000)
    return 70
}