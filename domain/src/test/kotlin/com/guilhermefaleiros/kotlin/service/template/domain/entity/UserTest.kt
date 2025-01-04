package com.guilhermefaleiros.kotlin.service.template.domain.entity

import com.github.javafaker.Faker
import java.util.UUID
import kotlin.test.Test
import kotlin.test.assertEquals

internal class UserTest {
    private val faker = Faker.instance()

    @Test
    fun `should create user`() {
        val expectedName = faker.name().fullName()
        val expectedEmail = faker.internet().emailAddress()

        val user = User.create(expectedName, expectedEmail)

        assertEquals(expectedName, user.name)
        assertEquals(expectedEmail, user.email)
    }

    @Test
    fun `should restore user`() {
        val expectedName = faker.name().fullName()
        val expectedEmail = faker.internet().emailAddress()
        val expectedId = UUID.randomUUID()

        val user = User.restore(expectedId, expectedName, expectedEmail)

        assertEquals(expectedName, user.name)
        assertEquals(expectedEmail, user.email)
        assertEquals(expectedId, user.id.value)
    }
}
