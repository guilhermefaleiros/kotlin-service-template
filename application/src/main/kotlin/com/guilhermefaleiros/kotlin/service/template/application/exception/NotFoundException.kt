package com.guilhermefaleiros.kotlin.service.template.application.exception

class NotFoundException(override val message: String) : RuntimeException(message)
