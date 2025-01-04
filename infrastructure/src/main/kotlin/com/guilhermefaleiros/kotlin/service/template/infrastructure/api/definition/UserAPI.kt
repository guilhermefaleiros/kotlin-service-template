package com.guilhermefaleiros.kotlin.service.template.infrastructure.api.definition

import com.guilhermefaleiros.kotlin.service.template.infrastructure.api.model.request.CreateUserRequest
import com.guilhermefaleiros.kotlin.service.template.infrastructure.api.model.response.RetrieveUserResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import java.util.UUID

@RequestMapping("/users")
interface UserAPI {
    @PostMapping
    fun create(
        @RequestBody request: CreateUserRequest,
    ): ResponseEntity<Any>

    @GetMapping("/{id}")
    fun getById(
        @PathVariable id: UUID,
    ): ResponseEntity<RetrieveUserResponse>

    @GetMapping
    fun listAll(): ResponseEntity<List<RetrieveUserResponse>>

    @DeleteMapping("/{id}")
    fun deleteById(
        @PathVariable id: UUID,
    ): ResponseEntity<Unit>
}
