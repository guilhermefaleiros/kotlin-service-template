package com.guilhermefaleiros.kotlin.service.template.infrastructure.api.definition

import com.guilhermefaleiros.kotlin.service.template.infrastructure.api.model.ErrorDTO
import com.guilhermefaleiros.kotlin.service.template.infrastructure.api.model.request.CreateUserRequest
import com.guilhermefaleiros.kotlin.service.template.infrastructure.api.model.response.CreateUserResponse
import com.guilhermefaleiros.kotlin.service.template.infrastructure.api.model.response.RetrieveUserResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
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
    @Operation(
        summary = "Create a new user", description = "Create a new user", responses = [
            ApiResponse(
                responseCode = "201", description = "User created", content = [
                    Content(
                        mediaType = "application/json",
                        schema = Schema(implementation = CreateUserResponse::class)
                    )
                ]
            ),
            ApiResponse(
                responseCode = "422", description = "Error to validate", content = [
                    Content(
                        mediaType = "application/json",
                        schema = Schema(implementation = ErrorDTO::class)
                    )
                ]
            ),
        ]
    )
    fun create(
        @RequestBody request: CreateUserRequest,
    ): ResponseEntity<*>

    @GetMapping("/{id}")
    @Operation(
        summary = "Get user by id", description = "Get user by id", responses = [
            ApiResponse(
                responseCode = "200", description = "User found", content = [
                    Content(
                        mediaType = "application/json",
                        schema = Schema(implementation = RetrieveUserResponse::class)
                    )
                ]
            ),
            ApiResponse(
                responseCode = "404", description = "User not found", content = [
                    Content(
                        mediaType = "application/json",
                        schema = Schema(implementation = ErrorDTO::class)
                    )
                ]
            ),
        ]
    )
    fun getById(
        @PathVariable id: UUID,
    ): ResponseEntity<RetrieveUserResponse>

    @GetMapping
    @Operation(
        summary = "List all users", description = "List all users", responses = [
            ApiResponse(
                responseCode = "200", description = "Users found", content = [
                    Content(
                        mediaType = "application/json",
                        schema = Schema(implementation = RetrieveUserResponse::class, type = "array")
                    )
                ]
            ),
        ]
    )
    fun listAll(): ResponseEntity<List<RetrieveUserResponse>>

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Delete user by id", description = "Delete user by id", responses = [
            ApiResponse(
                responseCode = "204", description = "User deleted"
            ),
        ]
    )
    fun deleteById(
        @PathVariable id: UUID,
    ): ResponseEntity<Unit>
}
