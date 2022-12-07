package com.goofy.springmultithread

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.time.ZoneId
import java.util.*

@SpringBootApplication
class SpringMultithreadApplication

fun main(args: Array<String>) {
    init()
    runApplication<SpringMultithreadApplication>(*args)
}

fun init() {
    TimeZone.setDefault(TimeZone.getTimeZone(ZoneId.of("Asia/Seoul")))
    System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "100")
}
