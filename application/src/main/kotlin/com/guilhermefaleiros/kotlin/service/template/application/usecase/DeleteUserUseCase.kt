package com.guilhermefaleiros.kotlin.service.template.application.usecase

import com.guilhermefaleiros.kotlin.service.template.application.repository.UserRepository
import com.guilhermefaleiros.kotlin.service.template.domain.base.UseCaseWithInput
import com.guilhermefaleiros.kotlin.service.template.domain.valueobject.EntityId
import java.util.UUID

class DeleteUserUseCase(
    private val userRepository: UserRepository,
) : UseCaseWithInput<DeleteUserUseCase.Input> {
    data class Input(
        val id: UUID,
    )

    override fun execute(input: Input) {
        userRepository.deleteById(EntityId.restore(input.id))
    }
}
