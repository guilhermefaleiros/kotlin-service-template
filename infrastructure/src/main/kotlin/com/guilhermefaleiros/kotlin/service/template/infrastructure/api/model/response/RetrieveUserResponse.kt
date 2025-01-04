package com.guilhermefaleiros.kotlin.service.template.infrastructure.api.model.response

import com.guilhermefaleiros.kotlin.service.template.application.usecase.RetrieveUserByIdUseCase

data class RetrieveUserResponse(
    val id: String,
    val name: String,
    val email: String,
) {
    companion object {
        fun from(output: RetrieveUserByIdUseCase.Output): RetrieveUserResponse {
            return RetrieveUserResponse(output.id, output.name, output.email)
        }
    }
}
