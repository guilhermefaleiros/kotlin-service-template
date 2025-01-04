package com.guilhermefaleiros.kotlin.service.template.infrastructure.e2e

import com.guilhermefaleiros.kotlin.service.template.infrastructure.IntegrationTest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

internal class GlobalExceptionHandlerE2ETest : IntegrationTest() {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `should return generic message on non-existent endpoint`() {
        mockMvc.perform(get("/non-existent-endpoint"))
            .andExpect(status().isInternalServerError)
            .andExpect(jsonPath("$.message").value("Internal server error"))
            .andExpect(jsonPath("$.errors").isArray)
            .andExpect(jsonPath("$.errors[0]").value("An error occurred"))
    }
}
