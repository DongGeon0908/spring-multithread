package com.goofy.springmultithread.presentation

import com.goofy.springmultithread.application.TodoService
import com.goofy.springmultithread.common.wrapOk
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/todos", produces = [MediaType.APPLICATION_JSON_VALUE])
class TodoController(
    private val todoService: TodoService
) {
    // testName=default insert test, testSize=1000, testTime=105409ms
    @PostMapping("/default-insert-test-1")
    fun defaultInsert() = todoService.defaultTest().wrapOk()

    // testName=default insert test, testSize=1000, testTime=11801ms
    @PostMapping("/async-insert-test-1")
    fun asyncInsert() = todoService.asyncInsert().wrapOk()
}
