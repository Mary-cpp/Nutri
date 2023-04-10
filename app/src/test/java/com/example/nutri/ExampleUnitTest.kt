package com.example.nutri

import kotlinx.coroutines.*
import org.junit.Assert.assertEquals
import org.junit.Test

class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun supervisorCoroutineTest() = runBlocking {
        supervisorCoroutine()
        return@runBlocking
    }

    @Test
    fun coroutineAsyncTest() = runBlocking {
        val deferredResult = CoroutineScope(Dispatchers.Default).async {
            delay(2000)
            println("finished async work")
            throw Exception("bla bla bla")
            5

        }
        try {
            deferredResult.cancel()
            deferredResult.await()
        }catch (e: CancellationException){ println("Caught an exception $e in $deferredResult") }
        return@runBlocking
    }

    @Test
    fun coroutineCancelTest() = runBlocking {
        var value = "Hello"
        try{
            value += doWorkCancellable().await()
        } catch(e: CancellationException){ println("Caught $e") }
        finally{
            println(value)
        }
        return@runBlocking
    }

    private suspend fun supervisorCoroutine() = supervisorScope {
        println("do supervisor job")
        delay(2000)
        // launch child scope where exception is produced
        doWorkWithException()
    }

    private fun doWorkCancellable() = CoroutineScope(Dispatchers.Unconfined).async {
        delay(5000)
        throw CancellationException()
        "World"
    }

    private fun doWorkWithException() = CoroutineScope(Dispatchers.IO).launch{
        println("start work that produces an exception")
        delay (5000)
        throw Exception("Gotcha!!!")
        doWork()
    }

    private suspend fun doWork(){
        delay(1000)
    }
}