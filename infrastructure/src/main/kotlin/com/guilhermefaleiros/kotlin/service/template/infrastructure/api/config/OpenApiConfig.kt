package com.guilhermefaleiros.kotlin.service.template.infrastructure.api.config

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.springframework.context.annotation.Configuration

@Configuration
@OpenAPIDefinition(
    info = Info(
        title = "Kotlin Service Template",
        version = "1.0.0",
        description = "Kotlin Service Template"
    )
)
open class OpenApiConfig