package com.guilhermefaleiros.kotlin.service.template.infrastructure.api.model.request

import com.guilhermefaleiros.kotlin.service.template.application.usecase.CreateUserUseCase

data class CreateUserRequest(
    val name: String,
    val email: String,
) {
    fun toInput() = CreateUserUseCase.Input(name, email)
}
