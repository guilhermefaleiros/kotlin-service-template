package com.guilhermefaleiros.kotlin.service.template.application.usecase

import com.github.javafaker.Faker
import com.guilhermefaleiros.kotlin.service.template.application.repository.UserRepository
import com.guilhermefaleiros.kotlin.service.template.domain.entity.User
import io.mockk.every
import io.mockk.mockk
import java.util.UUID
import kotlin.test.Test
import kotlin.test.assertEquals

internal class RetrieveUserByIdUseCaseTest {
    private val faker = Faker.instance()

    @Test
    fun `should retrieve user correctly`() {
        val user = User.create(faker.name().fullName(), faker.internet().emailAddress())

        val userRepository = mockk<UserRepository>()

        every { userRepository.findById(any()) } returns user

        val retrieveUserUseCase = RetrieveUserByIdUseCase(userRepository)

        val output = retrieveUserUseCase.execute(RetrieveUserByIdUseCase.Input(user.id.value))

        assertEquals(user.name, output.name)
        assertEquals(user.email, output.email)
    }

    @Test
    fun `should throw exception when user not found`() {
        val userRepository = mockk<UserRepository>()

        every { userRepository.findById(any()) } returns null

        val retrieveUserUseCase = RetrieveUserByIdUseCase(userRepository)

        val id = UUID.randomUUID()
        try {
            retrieveUserUseCase.execute(RetrieveUserByIdUseCase.Input(id))
        } catch (e: Exception) {
            assertEquals("User not found with id $id", e.message)
        }
    }
}
