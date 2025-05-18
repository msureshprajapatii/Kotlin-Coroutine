package com.demo.kotlin_coroutines

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.demo.kotlin_coroutines.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private fun updateCounter() {
        val count = binding.tvCounter.text.toString().toInt()
        val updatedCount = count + 1
        binding.tvCounter.text = updatedCount.toString()

        // Just Print Thread Name
        Log.d(TAG, "updateCounter: Current Thread = ${Thread.currentThread().name}")
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {

            btnUpdateCounter.setOnClickListener {
                // normal counter update with button ripple effect
                updateCounter()
            }

            btnExecuteTask.setOnClickListener {
                // Here executed long running task without thread and coroutine
                executeTask()
            }

            btnExecuteThreadTask.setOnClickListener {
                // Here executed long running task with thread
                executeTaskWithThread()
            }

            btnExecuteCoroutineTask.setOnClickListener {
                // Here executed long running task with coroutine
                executeTaskWithCoroutine()
            }
        }


        // Suspending functions
        CoroutineScope(Dispatchers.Main).launch {
            task1()
            task2()
        }


        // Coroutine Builders
        CoroutineScope(Dispatchers.IO).launch {

            //  Parallel
            launchWithoutJoin()

            // Sequential
            launchCoroutineBuilder()
            asyncCoroutineBuilder()
        }
    }

    // its give result as zero(0) because its not wait until coroutine task are complete
    //  its execute task in parallel manner
    private suspend fun launchWithoutJoin() {
        var fbFollowers = 0
        val job = lifecycleScope.launch(Dispatchers.IO) {
            fbFollowers = getFaceBookFollowers()
        }
        Log.e(TAG, "fbFollowers with launch without join : $fbFollowers")

    }

    // Launch Builder
    // job.Join() is help us to suspend coroutine until task is not complete
    // basically its execute task in sequentially manner
    private suspend fun launchCoroutineBuilder() {
        var fbFollowers = 0
        val job = lifecycleScope.launch(Dispatchers.IO) {
            fbFollowers = getFaceBookFollowers()
        }
        job.join()
        Log.e(TAG, "fbFollowers with launch and join : $fbFollowers")
    }

    // Async Builder
    // async.await() is help us to suspend coroutine until task is not complete
    // Basically its execute task in sequentially manner when we use async await
    // Benefit : Code concise compare to launch
    private suspend fun asyncCoroutineBuilder() {
        val job = lifecycleScope.async(Dispatchers.IO) {
            getFaceBookFollowers()
        }
        Log.e(TAG, "fbFollowers with async and await: ${job.await()}")
    }


    private suspend fun getFaceBookFollowers(): Int {
        delay(1000)
        return 45
    }


}