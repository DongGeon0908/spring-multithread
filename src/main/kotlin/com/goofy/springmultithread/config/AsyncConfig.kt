package com.goofy.springmultithread.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableAsync

@Configuration
@EnableAsync
class AsyncConfig(
    private val asyncGenerator: AsyncGenerator
) {
    @Bean(name = ["testExecutor"])
    fun testExecutor() = asyncGenerator.generateExecutor(threadName = "testExecutor")
}
