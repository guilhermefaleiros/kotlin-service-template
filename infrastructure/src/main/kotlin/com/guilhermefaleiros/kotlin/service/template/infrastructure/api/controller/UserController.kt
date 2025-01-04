package com.guilhermefaleiros.kotlin.service.template.infrastructure.api.controller

import com.guilhermefaleiros.kotlin.service.template.application.usecase.CreateUserUseCase
import com.guilhermefaleiros.kotlin.service.template.application.usecase.DeleteUserUseCase
import com.guilhermefaleiros.kotlin.service.template.application.usecase.RetrieveUserByIdUseCase
import com.guilhermefaleiros.kotlin.service.template.application.usecase.RetrieveUsersUseCase
import com.guilhermefaleiros.kotlin.service.template.infrastructure.api.definition.UserAPI
import com.guilhermefaleiros.kotlin.service.template.infrastructure.api.model.ErrorDTO
import com.guilhermefaleiros.kotlin.service.template.infrastructure.api.model.request.CreateUserRequest
import com.guilhermefaleiros.kotlin.service.template.infrastructure.api.model.response.CreateUserResponse
import com.guilhermefaleiros.kotlin.service.template.infrastructure.api.model.response.RetrieveUserResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import java.net.URI
import java.util.UUID

@RestController
class UserController(
    private val createUserUseCase: CreateUserUseCase,
    private val retrieveUserByIdUseCase: RetrieveUserByIdUseCase,
    private val retrieveUsersUseCase: RetrieveUsersUseCase,
    private val deleteUserByIdUseCase: DeleteUserUseCase,
) : UserAPI {
    override fun create(request: CreateUserRequest): ResponseEntity<*> {
        val output = createUserUseCase.execute(request.toInput())

        return output.fold(
            onLeft = { error ->
                ResponseEntity.unprocessableEntity().body(ErrorDTO("Error to validate", error.getErrors()))
            },
            onRight = { result ->
                val response = CreateUserResponse.from(result)
                ResponseEntity.created(URI.create("/users/" + response.id)).body(response)
            },
        )
    }

    override fun getById(id: UUID): ResponseEntity<RetrieveUserResponse> {
        val output = retrieveUserByIdUseCase.execute(RetrieveUserByIdUseCase.Input(id))
        return ResponseEntity.ok(RetrieveUserResponse.from(output))
    }

    override fun listAll(): ResponseEntity<List<RetrieveUserResponse>> {
        val output = retrieveUsersUseCase.execute()
        return ResponseEntity.ok(output.map { RetrieveUserResponse(it.id, it.name, it.email) })
    }

    override fun deleteById(id: UUID): ResponseEntity<Unit> {
        deleteUserByIdUseCase.execute(DeleteUserUseCase.Input(id))
        return ResponseEntity.noContent().build()
    }
}
