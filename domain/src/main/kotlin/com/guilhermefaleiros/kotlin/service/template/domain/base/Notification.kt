package com.guilhermefaleiros.kotlin.service.template.domain.base

class Notification {
    private val errors = mutableListOf<String>()

    fun addError(message: String) {
        errors.add(message)
    }

    fun hasErrors(): Boolean = errors.isNotEmpty()

    fun getErrors(): List<String> = errors

    override fun toString(): String {
        return errors.joinToString(separator = "; ")
    }
}
