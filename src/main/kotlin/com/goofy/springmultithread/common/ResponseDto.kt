package com.goofy.springmultithread.common

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

data class ResponseDto<T>(val data: T)

/**
 * Wrap Response Ok
 **/
fun <T> T.wrapOk() = ResponseEntity.ok(ResponseDto(this))

/**
 * Wrap Response Created
 **/
fun <T> T.wrapCreated() = ResponseEntity.status(HttpStatus.CREATED).body(ResponseDto(this))

/**
 * Wrap Response Void
 **/
fun Unit.wrapVoid() = ResponseEntity.noContent()
