package com.guilhermefaleiros.kotlin.service.template.domain.base

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class NotificationTest {
    @Test
    fun `should add an error message`() {
        val notification = Notification()
        val errorMessage = "Error occurred"

        notification.addError(errorMessage)

        assertTrue(notification.hasErrors())
        assertEquals(1, notification.getErrors().size)
        assertEquals(errorMessage, notification.getErrors().first())
    }

    @Test
    fun `should return true for hasErrors when errors exist`() {
        val notification = Notification()
        notification.addError("Error 1")
        notification.addError("Error 2")

        assertTrue(notification.hasErrors())
    }

    @Test
    fun `should return false for hasErrors when no errors exist`() {
        val notification = Notification()

        assertFalse(notification.hasErrors())
    }

    @Test
    fun `should return all added error messages`() {
        val notification = Notification()
        val errors = listOf("Error 1", "Error 2", "Error 3")

        errors.forEach { notification.addError(it) }

        assertEquals(errors.size, notification.getErrors().size)
        assertEquals(errors, notification.getErrors())
    }

    @Test
    fun `toString should return error messages joined by semicolon`() {
        val notification = Notification()
        val errors = listOf("Error 1", "Error 2", "Error 3")

        errors.forEach { notification.addError(it) }

        val expectedString = "Error 1; Error 2; Error 3"
        assertEquals(expectedString, notification.toString())
    }

    @Test
    fun `toString should return empty string when no errors exist`() {
        val notification = Notification()

        assertEquals("", notification.toString())
    }
}
