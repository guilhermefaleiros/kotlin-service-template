package com.guilhermefaleiros.kotlin.service.template.domain.base

interface UseCase<IN, OUT> {
    fun execute(input: IN): OUT
}

interface UseCaseWithOutput<OUT> {
    fun execute(): OUT
}

interface UseCaseWithInput<IN> {
    fun execute(input: IN)
}
