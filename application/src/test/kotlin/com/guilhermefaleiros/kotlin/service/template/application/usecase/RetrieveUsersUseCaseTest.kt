package com.guilhermefaleiros.kotlin.service.template.application.usecase

import com.github.javafaker.Faker
import com.guilhermefaleiros.kotlin.service.template.application.repository.UserRepository
import com.guilhermefaleiros.kotlin.service.template.domain.entity.User
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class RetrieveUsersUseCaseTest {
    private val faker = Faker.instance()

    @Test
    fun `should retrieve users correctly`() {
        val firstUser = User.create(faker.name().fullName(), faker.internet().emailAddress())
        val secondUser = User.create(faker.name().fullName(), faker.internet().emailAddress())

        val userRepository = mockk<UserRepository>()

        every { userRepository.findAll() } returns listOf(firstUser, secondUser)

        val retrieveUsersUseCase = RetrieveUsersUseCase(userRepository)

        val output = retrieveUsersUseCase.execute()

        assertEquals(2, output.size)
        assertEquals(firstUser.name, output[0].name)
        assertEquals(firstUser.email, output[0].email)
        assertEquals(secondUser.name, output[1].name)
        assertEquals(secondUser.email, output[1].email)
    }

    @Test
    fun `should return empty list when no users`() {
        val userRepository = mockk<UserRepository>()

        every { userRepository.findAll() } returns emptyList()

        val retrieveUsersUseCase = RetrieveUsersUseCase(userRepository)

        val output = retrieveUsersUseCase.execute()

        assertEquals(0, output.size)
    }
}
