package com.goofy.springmultithread.application

import mu.KotlinLogging
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import java.util.concurrent.CompletableFuture

@Service
class TestService {
    private val logger = KotlinLogging.logger {}

    fun getNumbers1(): List<String> {
        val a = (0..30000000).map { "happy$it" }
        logger.info { "number 1 exit" }
        return a
    }

    fun getNumbers2(): List<String> {
        val a = (0..30000000).map { "good$it" }
        logger.info { "number 2 exit" }
        return a
    }

    fun getNumbers3(): List<String> {
        val a = (0..30000000).map { "easy$it" }
        logger.info { "number 3 exit" }
        return a
    }

    fun getNumbers4(): List<String> {
        val a = (0..30000000).map { "fun$it" }
        logger.info { "number 4 exit" }
        return a
    }

    @Async(value = "testExecutor")
    fun asyncGetNumbers1(): CompletableFuture<List<String>> {
        return CompletableFuture.completedFuture(getNumbers1())
    }

    @Async(value = "testExecutor")
    fun asyncGetNumbers2(): CompletableFuture<List<String>> {
        return CompletableFuture.completedFuture(getNumbers2())
    }

    @Async(value = "testExecutor")
    fun asyncGetNumbers3(): CompletableFuture<List<String>> {
        return CompletableFuture.completedFuture(getNumbers3())
    }

    @Async(value = "testExecutor")
    fun asyncGetNumbers4(): CompletableFuture<List<String>> {
        return CompletableFuture.completedFuture(getNumbers4())
    }
}
