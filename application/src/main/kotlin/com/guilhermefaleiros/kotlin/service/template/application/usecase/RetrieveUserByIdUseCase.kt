package com.guilhermefaleiros.kotlin.service.template.application.usecase

import com.guilhermefaleiros.kotlin.service.template.application.exception.NotFoundException
import com.guilhermefaleiros.kotlin.service.template.application.repository.UserRepository
import com.guilhermefaleiros.kotlin.service.template.domain.base.UseCase
import com.guilhermefaleiros.kotlin.service.template.domain.valueobject.EntityId
import java.util.UUID

class RetrieveUserByIdUseCase(
    private val userRepository: UserRepository,
) : UseCase<RetrieveUserByIdUseCase.Input, RetrieveUserByIdUseCase.Output> {
    data class Input(
        val id: UUID,
    )

    data class Output(
        val id: String,
        val name: String,
        val email: String,
    )

    override fun execute(input: Input): Output {
        val user =
            userRepository.findById(EntityId.restore(input.id))
                ?: throw NotFoundException("User not found with id ${input.id}")
        return Output(id = user.id.value.toString(), name = user.name, email = user.email)
    }
}
