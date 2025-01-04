package com.guilhermefaleiros.kotlin.service.template.application.usecase

import com.github.javafaker.Faker
import com.guilhermefaleiros.kotlin.service.template.application.repository.UserRepository
import com.guilhermefaleiros.kotlin.service.template.domain.base.Either
import io.mockk.mockk
import io.mockk.verify
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

internal class CreateUserUseCaseTest {
    private val faker = Faker.instance()

    @Test
    fun `should create user correctly`() {
        val input =
            CreateUserUseCase.Input(
                name = faker.name().fullName(),
                email = faker.internet().emailAddress(),
            )

        val userRepository = mockk<UserRepository>(relaxed = true)
        val createUserUseCase = CreateUserUseCase(userRepository)

        val output = createUserUseCase.execute(input)

        assertTrue(output is Either.Right)
        val rightOutput = output.value
        assertNotNull(rightOutput.id)

        verify { userRepository.save(any()) }
    }

    @Test
    fun `should return validation errors when input is invalid`() {
        val input =
            CreateUserUseCase.Input(
                name = "",
                email = "",
            )

        val userRepository = mockk<UserRepository>(relaxed = true)
        val createUserUseCase = CreateUserUseCase(userRepository)

        val output = createUserUseCase.execute(input)

        assertTrue(output is Either.Left)
        val leftOutput = output.value

        assertTrue(leftOutput.hasErrors())
        assertEquals(2, leftOutput.getErrors().size)
        assertEquals("Name is required", leftOutput.getErrors()[0])
        assertEquals("Email is required", leftOutput.getErrors()[1])

        // Verifica que o repositório não foi chamado
        verify(exactly = 0) { userRepository.save(any()) }
    }
}
