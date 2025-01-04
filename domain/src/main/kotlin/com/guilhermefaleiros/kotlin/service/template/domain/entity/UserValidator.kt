package com.guilhermefaleiros.kotlin.service.template.domain.entity

import com.guilhermefaleiros.kotlin.service.template.domain.base.Notification
import com.guilhermefaleiros.kotlin.service.template.domain.base.Validator

class UserValidator : Validator<User> {
    override fun validate(entity: User): Notification {
        val notification = Notification()
        if (entity.name.isBlank()) {
            notification.addError("Name is required")
        }
        if (entity.email.isBlank()) {
            notification.addError("Email is required")
        }
        return notification
    }
}
