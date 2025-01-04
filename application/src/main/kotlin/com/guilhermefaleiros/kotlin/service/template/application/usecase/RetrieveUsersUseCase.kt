package com.guilhermefaleiros.kotlin.service.template.application.usecase

import com.guilhermefaleiros.kotlin.service.template.application.repository.UserRepository
import com.guilhermefaleiros.kotlin.service.template.domain.base.UseCaseWithOutput

class RetrieveUsersUseCase(
    private val userRepository: UserRepository,
) : UseCaseWithOutput<List<RetrieveUsersUseCase.Output>> {
    data class Output(
        val id: String,
        val email: String,
        val name: String,
    )

    override fun execute(): List<Output> {
        return userRepository.findAll().map {
            Output(id = it.id.value.toString(), email = it.email, name = it.name)
        }
    }
}
