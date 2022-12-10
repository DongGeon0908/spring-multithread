package com.goofy.springmultithread.application

import com.goofy.springmultithread.domain.Todo
import com.goofy.springmultithread.domain.vo.TodoCategory
import com.goofy.springmultithread.dto.TodoResponse
import com.goofy.springmultithread.infrastructure.TodoRepository
import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.ZonedDateTime
import java.util.concurrent.CompletableFuture

@Service
class TodoService(
    private val todoRepository: TodoRepository,
    private val todoAsyncService: TodoAsyncService
) {
    private val logger = KotlinLogging.logger {}

    @Transactional
    fun defaultTest(): TodoResponse {
        val begin = System.currentTimeMillis()

        val todos = (0 until 1000).map { ofTodo(it) }
        todoRepository.saveAll(todos)

        val end = System.currentTimeMillis()


        val response = TodoResponse(
            testName = "default insert test",
            testSize = 1000,
            testTime = (end - begin).toString() + "ms"
        )

        logger.info { response.toString() }

        return response
    }

    // TODO : 병렬 처리 후, 모아주는 기능에 대해 학습 필요
    @Transactional
    fun asyncInsert(): TodoResponse {
        val begin = System.currentTimeMillis()

        val todos = (0 until 1000).map { todoAsyncService.asyncCreateTodo(it) }

        CompletableFuture.allOf(*todos.toTypedArray()).join()

        todoRepository.saveAll(todos.map { it.get() })

        val end = System.currentTimeMillis()


        val response = TodoResponse(
            testName = "default insert test",
            testSize = 1000,
            testTime = (end - begin).toString() + "ms"
        )

        logger.info { response.toString() }

        return response
    }

    private fun ofTodo(number: Int): Todo {
        Thread.sleep(100)

        val title = number.toString() + "default test + title" + ZonedDateTime.now()
        val content = number.toString() + "default test + content" + ZonedDateTime.now()

        val todo = Todo(
            title = title,
            content = content,
            category = TodoCategory.MISC
        )

        logger.info { todo.toString() }

        return todo
    }
}
