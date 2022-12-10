package com.goofy.springmultithread.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor

@Configuration
@EnableAsync
class AsyncConfig {
    /**
     * CorePoolSize : 기본 실행 대기하는 Thread의 수
     * MaxPoolSize : 동시 동작하는 최대 Thread의 수
     * QueueCapacity : MaxPoolSize 초과 요청에서 Thread 생성 요청시, 해당 요청을 Queue에 저장하는데 이때 최대 수용 가능한 Queue의 수,Queue에 저장되어있다가 Thread에 자리가 생기면 하나씩 빠져나가 동작
     * ThreadNamePrefix : 생성되는 Thread 접두사 지정
     **/
    @Bean(name = ["testExecutor"])
    fun testExecutor() = ThreadPoolTaskExecutor()
        .apply {
            this.corePoolSize = 10
            this.maxPoolSize = 50
            this.queueCapacity = 100
            this.setThreadNamePrefix("testExecutor-")
            this.initialize()
        }
}
