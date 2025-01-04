package com.guilhermefaleiros.kotlin.service.template.application.usecase

import com.guilhermefaleiros.kotlin.service.template.application.repository.UserRepository
import com.guilhermefaleiros.kotlin.service.template.domain.base.Either
import com.guilhermefaleiros.kotlin.service.template.domain.base.Notification
import com.guilhermefaleiros.kotlin.service.template.domain.base.UseCase
import com.guilhermefaleiros.kotlin.service.template.domain.entity.User

class CreateUserUseCase(
    private val userRepository: UserRepository,
) : UseCase<CreateUserUseCase.Input, Either<Notification, CreateUserUseCase.Output>> {
    data class Input(
        val name: String,
        val email: String,
    )

    data class Output(
        val id: String,
    )

    override fun execute(input: Input): Either<Notification, Output> {
        val user = User.create(input.name, input.email)
        val notification = user.validate()
        if (notification.hasErrors()) {
            return Either.Left(notification)
        }
        userRepository.save(user)
        return Either.Right(Output(user.id.value.toString()))
    }
}
