package com.goofy.springmultithread.config

import mu.KotlinLogging
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import org.springframework.stereotype.Component

@Component
class AsyncGenerator {
    private val logger = KotlinLogging.logger {}

    companion object {
        const val DEFAULT_CORE_POOL_SIZE = 5
        const val DEFAULT_MAX_POOL_SIZE = 10
        const val DEFAULT_QUEUE_CAPACITY = 10
    }

    /**
     * generateExecutor
     *
     * - CorePoolSize : 기본 실행 대기하는 Thread의 수
     * - MaxPoolSize : 동시 동작하는 최대 Thread의 수
     * - QueueCapacity : MaxPoolSize 초과 요청에서 Thread 생성 요청시, 해당 요청을 Queue에 저장하는데 이때 최대 수용 가능한 Queue의 수,Queue에 저장되어있다가 Thread에 자리가 생기면 하나씩 빠져나가 동작
     * - ThreadNamePrefix : 생성되는 Thread 접두사 지정
     **/
    fun generateExecutor(
        threadName: String,
        corePoolSize: Int = DEFAULT_CORE_POOL_SIZE,
        maxPoolSize: Int = DEFAULT_MAX_POOL_SIZE,
        queueCapacity: Int = DEFAULT_QUEUE_CAPACITY
    ): ThreadPoolTaskExecutor {
        val threadPoolTaskExecutor = ThreadPoolTaskExecutor()
            .apply {
                this.corePoolSize = corePoolSize
                this.maxPoolSize = maxPoolSize
                this.queueCapacity = queueCapacity
                this.setThreadNamePrefix("$threadName-")
                this.setTaskDecorator(AsyncTaskDecorator())
                this.setRejectedExecutionHandler(AsyncRejectedExecutionHandler())
                this.initialize()
            }

        logger.info { "generate ThreadPoolTaskExecutor / threadName $threadName / corePoolSize $corePoolSize / maxPoolSize $maxPoolSize / queueCapacity $queueCapacity" }

        return threadPoolTaskExecutor
    }
}
