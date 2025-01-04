package com.guilhermefaleiros.kotlin.service.template.infrastructure.api.model

data class ErrorDTO(
    val message: String,
    val errors: List<String>,
)
