package com.guilhermefaleiros.kotlin.service.template.domain.valueobject

import java.util.UUID

data class EntityId(
    val value: UUID,
) {
    companion object {
        fun generate(): EntityId = EntityId(UUID.randomUUID())

        fun restore(value: UUID): EntityId = EntityId(value)
    }
}
