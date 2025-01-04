package com.guilhermefaleiros.kotlin.service.template.domain.base

interface Validator<T> {
    fun validate(entity: T): Notification
}
