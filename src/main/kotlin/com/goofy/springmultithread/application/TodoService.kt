package com.goofy.springmultithread.application

import com.goofy.springmultithread.domain.Todo
import com.goofy.springmultithread.domain.vo.TodoCategory
import com.goofy.springmultithread.dto.TodoResponse
import com.goofy.springmultithread.infrastructure.TodoRepository
import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.ZonedDateTime

@Service
class TodoService(
    private val todoRepository: TodoRepository
) {
    private val logger = KotlinLogging.logger {}

    @Transactional
    fun test(): TodoResponse {
        val begin = System.currentTimeMillis()

        val todos = (0 until 1000).map {
            val todo = ofTodo(it)

            logger.info { todo.toString() }

            todo
        }
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

    private fun ofTodo(number: Int): Todo {
        Thread.sleep(100)

        val title = number.toString() + "default test + title" + ZonedDateTime.now()
        val content = number.toString() + "default test + content" + ZonedDateTime.now()

        return Todo(
            title = title,
            content = content,
            category = TodoCategory.MISC
        )
    }
}
