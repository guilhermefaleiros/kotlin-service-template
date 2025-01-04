package com.guilhermefaleiros.kotlin.service.template.infrastructure.api.model.response

import com.fasterxml.jackson.annotation.JsonProperty
import com.guilhermefaleiros.kotlin.service.template.application.usecase.CreateUserUseCase

data class CreateUserResponse(
    @JsonProperty("id") val id: String,
) {
    companion object {
        fun from(output: CreateUserUseCase.Output): CreateUserResponse {
            return CreateUserResponse(output.id)
        }
    }
}
