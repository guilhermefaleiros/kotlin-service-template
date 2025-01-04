package com.guilhermefaleiros.kotlin.service.template.application.usecase

import com.guilhermefaleiros.kotlin.service.template.application.repository.UserRepository
import com.guilhermefaleiros.kotlin.service.template.domain.valueobject.EntityId
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import java.util.UUID

internal class DeleteUserUseCaseTest {
    @Test
    fun `should delete user correctly`() {
        val id = UUID.randomUUID()

        val userRepository = mockk<UserRepository>()

        every { userRepository.deleteById(any()) } returns Unit

        val deleteUserUseCase = DeleteUserUseCase(userRepository)

        deleteUserUseCase.execute(DeleteUserUseCase.Input(id))

        verify(exactly = 1) { userRepository.deleteById(EntityId.restore(id)) }
    }
}
