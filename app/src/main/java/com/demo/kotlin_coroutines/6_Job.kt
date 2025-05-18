package com.demo.kotlin_coroutines

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

const val JOB_TAG = "parent_child_job"
const val JOB_TAG2 = "cancel_parent_job"
const val JOB_TAG3 = "cancel_child_job"
const val JOB_TAG4 = "execute_long_running_task"

suspend fun executeParentChildJob() {

    val parentJob = CoroutineScope(Dispatchers.Default).launch {

        Log.e(JOB_TAG, "parent job started")

        val childJob1 = launch {
            Log.e(JOB_TAG, "child job started")
            delay(300)
            Log.e(JOB_TAG, "child job ended")
        }

        childJob1.join()
        Log.e(JOB_TAG, "parent job ended")
    }
}

// Cancel parent job

suspend fun executeParentChildJobCancel() {

    val parentJob = CoroutineScope(Dispatchers.Default).launch {

        Log.e(JOB_TAG2, "parent job started")

        val childJob1 = launch {
            Log.e(JOB_TAG2, "child job started")
            delay(300)
            Log.e(JOB_TAG2, "child job ended")
        }
    }

    delay(100)
    parentJob.cancel()
    Log.e(JOB_TAG2, "parent job cancelled")
}

suspend fun executeChildJobCancel() {

    val parentJob = CoroutineScope(Dispatchers.Default).launch {

        Log.e(JOB_TAG3, "parent job started")

        val childJob1 = launch {
            Log.e(JOB_TAG3, "child job started")
            delay(300)
            Log.e(JOB_TAG3, "child job ended")
        }

        delay(200)
        childJob1.cancel()
        Log.e(JOB_TAG3, "child job cancelled")
    }

    parentJob.join()
    Log.e(JOB_TAG3, "parent job ended")
}


// Execute long running function

suspend fun executeLongRunningTask() {

    // here without checking coroutine isActive, task is still running even job cancel before completing task

    val parentJob = CoroutineScope(Dispatchers.IO).launch {

        for (i in 1..1000) {
          if (isActive){
              longRunningFunction()
              Log.d(JOB_TAG4, "executeLongRunningTask: $i")
          }
        }
    }

    delay(500)
    Log.d(JOB_TAG4, "job cancelled")
    parentJob.cancel()
    parentJob.join()
    Log.d(JOB_TAG4, "parent completed")

}


fun longRunningFunction() {

    for (i in 1..1000000000) {

    }
}

