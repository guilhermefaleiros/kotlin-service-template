package com.guilhermefaleiros.kotlin.service.template.infrastructure.config.bean

import com.guilhermefaleiros.kotlin.service.template.application.repository.UserRepository
import com.guilhermefaleiros.kotlin.service.template.application.usecase.CreateUserUseCase
import com.guilhermefaleiros.kotlin.service.template.application.usecase.DeleteUserUseCase
import com.guilhermefaleiros.kotlin.service.template.application.usecase.RetrieveUserByIdUseCase
import com.guilhermefaleiros.kotlin.service.template.application.usecase.RetrieveUsersUseCase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class UseCaseConfiguration(
    private val userRepository: UserRepository,
) {
    @Bean
    open fun createUserUseCase(): CreateUserUseCase {
        return CreateUserUseCase(userRepository)
    }

    @Bean
    open fun retrieveUsersUseCase(): RetrieveUsersUseCase {
        return RetrieveUsersUseCase(userRepository)
    }

    @Bean
    open fun deleteUserUseCase(): DeleteUserUseCase {
        return DeleteUserUseCase(userRepository)
    }

    @Bean
    open fun retrieveUserByIdUseCase(): RetrieveUserByIdUseCase {
        return RetrieveUserByIdUseCase(userRepository)
    }
}
