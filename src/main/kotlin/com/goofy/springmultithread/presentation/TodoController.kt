package com.goofy.springmultithread.presentation

import com.goofy.springmultithread.application.TestAsyncService
import com.goofy.springmultithread.application.TodoService
import com.goofy.springmultithread.common.wrapOk
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/todos", produces = [MediaType.APPLICATION_JSON_VALUE])
class TodoController(
    private val todoService: TodoService,
    private val testAsyncService: TestAsyncService
) {
    // testName=default insert test, testSize=1000, testTime=105409ms
    @PostMapping("/default-insert-test-1")
    fun defaultInsert() = todoService.defaultTest().wrapOk()

    // testName=default insert test, testSize=1000, testTime=11801ms
    @PostMapping("/async-insert-test-1")
    fun asyncInsert() = todoService.asyncInsert().wrapOk()

    // TEST - 동기
    @GetMapping("/test-1")
    fun test1() = testAsyncService.testDefault().wrapOk()

    // TEST - 비동기
    @GetMapping("/test-2")
    fun test2() = testAsyncService.testAsync().wrapOk()
}
