package com.guilhermefaleiros.kotlin.service.template.domain.entity

import com.guilhermefaleiros.kotlin.service.template.domain.base.Notification
import com.guilhermefaleiros.kotlin.service.template.domain.valueobject.EntityId
import java.util.UUID

class User private constructor(
    val id: EntityId,
    val name: String,
    val email: String,
) {
    companion object {
        fun create(
            name: String,
            email: String,
        ): User {
            return User(EntityId.generate(), name, email)
        }

        fun restore(
            id: UUID,
            name: String,
            email: String,
        ): User {
            return User(EntityId.restore(id), name, email)
        }
    }

    fun validate(): Notification {
        return UserValidator().validate(this)
    }
}
