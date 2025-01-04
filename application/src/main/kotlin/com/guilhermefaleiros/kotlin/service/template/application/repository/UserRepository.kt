package com.guilhermefaleiros.kotlin.service.template.application.repository

import com.guilhermefaleiros.kotlin.service.template.domain.entity.User
import com.guilhermefaleiros.kotlin.service.template.domain.valueobject.EntityId

interface UserRepository {
    fun save(user: User)

    fun findById(id: EntityId): User?

    fun deleteById(id: EntityId)

    fun findAll(): List<User>
}
