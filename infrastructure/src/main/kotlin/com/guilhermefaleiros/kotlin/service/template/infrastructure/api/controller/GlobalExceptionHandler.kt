package com.guilhermefaleiros.kotlin.service.template.infrastructure.api.controller

import com.guilhermefaleiros.kotlin.service.template.application.exception.NotFoundException
import com.guilhermefaleiros.kotlin.service.template.infrastructure.api.model.ErrorDTO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(value = [NotFoundException::class])
    fun handleNotFoundException(ex: NotFoundException): ResponseEntity<*> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(
                ErrorDTO(
                    "Resource not found",
                    listOf(ex.message),
                ),
            )
    }

    @ExceptionHandler(value = [Exception::class])
    fun handleException(ex: Exception): ResponseEntity<*> {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(
                ErrorDTO(
                    "Internal server error",
                    listOf("An error occurred"),
                ),
            )
    }
}
