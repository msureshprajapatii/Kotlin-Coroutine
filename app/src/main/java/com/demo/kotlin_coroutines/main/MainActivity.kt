package com.demo.kotlin_coroutines.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.demo.kotlin_coroutines.basic.TAG
import com.demo.kotlin_coroutines.basic.asyncCoroutineBuilder
import com.demo.kotlin_coroutines.basic.executeChildJobCancel
import com.demo.kotlin_coroutines.basic.executeLongRunningTask
import com.demo.kotlin_coroutines.basic.executeParentChildJob
import com.demo.kotlin_coroutines.basic.executeParentChildJobCancel
import com.demo.kotlin_coroutines.basic.executeTask
import com.demo.kotlin_coroutines.basic.executeTaskWithCoroutine
import com.demo.kotlin_coroutines.basic.executeTaskWithThread
import com.demo.kotlin_coroutines.basic.launchCoroutineBuilder
import com.demo.kotlin_coroutines.basic.launchWithoutJoin
import com.demo.kotlin_coroutines.basic.performTaskInWithContext
import com.demo.kotlin_coroutines.basic.printFollowersWithLaunch
import com.demo.kotlin_coroutines.basic.secondScenarioWithLaunch
import com.demo.kotlin_coroutines.basic.task1
import com.demo.kotlin_coroutines.basic.task2
import com.demo.kotlin_coroutines.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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

            // check suspend function exception
            printFollowersWithLaunch()

            // Launch different scenarios
            secondScenarioWithLaunch()

            // Child parent job hierarchy
            executeParentChildJob()

            // Cancel parent job
            executeParentChildJobCancel()

            // cancel child job
            executeChildJobCancel()

            // Execute long running task
            executeLongRunningTask()

            // withContext
            performTaskInWithContext()
        }
    }
}