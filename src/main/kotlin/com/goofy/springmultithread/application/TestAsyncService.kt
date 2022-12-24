package com.goofy.springmultithread.application

import org.springframework.stereotype.Service
import java.util.concurrent.CompletableFuture

@Service
class TestAsyncService(
    private val testService: TestService
) {
    fun testDefault() {
        val start = System.currentTimeMillis()

        testService.getNumbers1()
        testService.getNumbers2()
        testService.getNumbers3()
        testService.getNumbers4()

        val end = System.currentTimeMillis()
        println(end - start)
    }

    fun testAsync() {
        val start = System.currentTimeMillis()

        val a = testService.asyncGetNumbers1()
        val b = testService.asyncGetNumbers2()
        val c = testService.asyncGetNumbers3()
        val d = testService.asyncGetNumbers4()

        // 함께 끝내요~
        CompletableFuture.allOf(
            a, b, c, d
        ).join()

        val end = System.currentTimeMillis()
        println(end - start)
    }
}
