package com.guilhermefaleiros.kotlin.service.template.domain.base

import kotlin.test.Test
import kotlin.test.assertEquals

internal class EitherTest {
    @Test
    fun `map should transform Right value`() {
        val either: Either<Nothing, Int> = Either.Right(5)
        val result = either.map { it * 2 }

        assertEquals(Either.Right(10), result)
    }

    @Test
    fun `map should not transform Left value`() {
        val either: Either<String, Int> = Either.Left("Error")
        val result = either.map { it * 2 }

        assertEquals(Either.Left("Error"), result)
    }

    @Test
    fun `mapLeft should transform Left value`() {
        val either: Either<String, Nothing> = Either.Left("Error")
        val result = either.mapLeft { it.uppercase() }

        assertEquals(Either.Left("ERROR"), result)
    }

    @Test
    fun `mapLeft should not transform Right value`() {
        val either: Either<String, Int> = Either.Right(42)
        val result = either.mapLeft { it.uppercase() }

        assertEquals(Either.Right(42), result)
    }

    @Test
    fun `fold should apply onRight when Right instance`() {
        val either: Either<Nothing, Int> = Either.Right(5)
        val result =
            either.fold(
                onLeft = { "Error" },
                onRight = { "Result: $it" },
            )

        assertEquals("Result: 5", result)
    }

    @Test
    fun `fold should apply onLeft when Left instance`() {
        val either: Either<String, Nothing> = Either.Left("Error")
        val result =
            either.fold(
                onLeft = { "Failed: $it" },
                onRight = { "Success: $it" },
            )

        assertEquals("Failed: Error", result)
    }

    @Test
    fun `map with complex transformation`() {
        val either: Either<Nothing, String> = Either.Right("test")
        val result = either.map { it.uppercase() }

        assertEquals(Either.Right("TEST"), result)
    }

    @Test
    fun `mapLeft with complex transformation`() {
        val either: Either<String, Nothing> = Either.Left("error")
        val result = either.mapLeft { it.reversed() }

        assertEquals(Either.Left("rorre"), result)
    }
}
