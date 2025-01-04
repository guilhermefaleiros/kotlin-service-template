package com.guilhermefaleiros.kotlin.service.template.domain.valueobject

import java.util.UUID
import kotlin.test.Test
import kotlin.test.assertEquals

internal class EntityIdTest {
    @Test
    fun `should generate entity id`() {
        val entityId = EntityId.generate()
        assert(entityId.value.toString().isNotBlank())
    }

    @Test
    fun `should restore entity id`() {
        val expectedValue = UUID.randomUUID()

        val entityId = EntityId.restore(expectedValue)
        assertEquals(expectedValue, entityId.value)
    }
}
