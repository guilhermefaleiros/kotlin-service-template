package com.guilhermefaleiros.kotlin.service.template.infrastructure.e2e

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.javafaker.Faker
import com.guilhermefaleiros.kotlin.service.template.domain.entity.User
import com.guilhermefaleiros.kotlin.service.template.infrastructure.IntegrationTest
import com.guilhermefaleiros.kotlin.service.template.infrastructure.api.model.request.CreateUserRequest
import com.guilhermefaleiros.kotlin.service.template.infrastructure.api.model.response.CreateUserResponse
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.util.UUID

internal class UserE2ETest : IntegrationTest() {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    private val faker: Faker = Faker.instance()

    companion object {
        private const val USERS_ENDPOINT = "/users"
        private const val ERROR_VALIDATION_MESSAGE = "Error to validate"
        private const val RESOURCE_NOT_FOUND_MESSAGE = "Resource not found"
    }

    @BeforeEach
    fun setUp() {
        clearDatabase()
    }

    // --- Tests for GET /users ---

    @Test
    fun `GET users returns empty list when no data exists`() {
        mockMvc.perform(get(USERS_ENDPOINT).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(content().json("[]"))
    }

    @Test
    fun `GET users returns user list with data`() {
        val user = createUser()

        mockMvc.perform(get(USERS_ENDPOINT).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$[0].name").value(user.name))
            .andExpect(jsonPath("$[0].email").value(user.email))
    }

    // --- Tests for GET /users/{id} ---

    @Test
    fun `GET user by id returns user data`() {
        val user = createUser()

        mockMvc.perform(
            get("$USERS_ENDPOINT/${user.id.value}")
                .contentType(MediaType.APPLICATION_JSON),
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.name").value(user.name))
            .andExpect(jsonPath("$.email").value(user.email))
    }

    @Test
    fun `GET user by id returns 404 when user not found`() {
        val id = UUID.randomUUID()

        mockMvc.perform(get("$USERS_ENDPOINT/$id").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound)
            .andExpect(jsonPath("$.message").value(RESOURCE_NOT_FOUND_MESSAGE))
            .andExpect(jsonPath("$.errors[0]").value("User not found with id $id"))
    }

    // --- Tests for POST /users ---

    @Test
    fun `POST user creates user successfully`() {
        createUser()
    }

    @Test
    fun `POST user fails with invalid fields`() {
        val request = CreateUserRequest(name = "", email = "")

        mockMvc.perform(
            post(USERS_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)),
        )
            .andExpect(status().isUnprocessableEntity)
            .andExpect(jsonPath("$.message").value(ERROR_VALIDATION_MESSAGE))
            .andExpect(jsonPath("$.errors[0]").value("Name is required"))
            .andExpect(jsonPath("$.errors[1]").value("Email is required"))
    }

    // --- Tests for DELETE /users/{id} ---

    @Test
    fun `DELETE user by id deletes user successfully`() {
        val user = createUser()

        mockMvc.perform(
            delete("$USERS_ENDPOINT/${user.id.value}")
                .contentType(MediaType.APPLICATION_JSON),
        )
            .andExpect(status().isNoContent)
    }

    // --- Helper Methods ---

    private fun createUser(): User {
        val request =
            CreateUserRequest(
                name = faker.name().fullName(),
                email = faker.internet().emailAddress(),
            )

        val result =
            mockMvc.perform(
                post(USERS_ENDPOINT)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request)),
            )
                .andExpect(status().isCreated)
                .andReturn()

        val response = objectMapper.readValue(result.response.contentAsString, CreateUserResponse::class.java)

        return User.restore(UUID.fromString(response.id), request.name, request.email)
    }
}
