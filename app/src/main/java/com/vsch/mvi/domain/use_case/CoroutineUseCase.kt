package com.vsch.mvi.domain.use_case

interface CoroutineUseCase<out Output, in Parameters> {

    suspend fun invoke(parameters: Parameters? = null): Output

}