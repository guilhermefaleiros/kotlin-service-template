package com.guilhermefaleiros.kotlin.service.template.domain.base

sealed class Either<out L, out R> {
    data class Left<out L>(val value: L) : Either<L, Nothing>()

    data class Right<out R>(val value: R) : Either<Nothing, R>()

    inline fun <T> map(transform: (R) -> T): Either<L, T> =
        when (this) {
            is Left -> this
            is Right -> Right(transform(value))
        }

    inline fun <T> mapLeft(transform: (L) -> T): Either<T, R> =
        when (this) {
            is Left -> Left(transform(value))
            is Right -> this
        }

    inline fun <T> fold(
        onLeft: (L) -> T,
        onRight: (R) -> T,
    ): T =
        when (this) {
            is Left -> onLeft(value)
            is Right -> onRight(value)
        }
}
