package com.goofy.springmultithread.application

import com.goofy.springmultithread.domain.Todo
import com.goofy.springmultithread.domain.vo.TodoCategory
import mu.KotlinLogging
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import java.time.ZonedDateTime
import java.util.concurrent.CompletableFuture

@Service
class TodoAsyncService {
    private val logger = KotlinLogging.logger {}

    @Async("testExecutor")
    fun asyncCreateTodo(number: Int): CompletableFuture<Todo> {
        Thread.sleep(100)

        val title = number.toString() + "default test + title" + ZonedDateTime.now()
        val content = number.toString() + "default test + content" + ZonedDateTime.now()

        val todo = Todo(
            title = title,
            content = content,
            category = TodoCategory.MISC
        )

        logger.info { todo.toString() }

        return CompletableFuture.completedFuture(todo)
    }
}
