package com.vsch.mvi.domain.use_case

import com.vsch.mvi.domain.entities.Todo
import com.vsch.mvi.domain.repository.TodoRepository
import javax.inject.Inject

interface IGetTodosUseCase: CoroutineUseCase<List<Todo>, Unit>

class GetTodosUseCase @Inject constructor(
    private val todoRepository: TodoRepository
): IGetTodosUseCase {

    override suspend fun invoke(parameters: Unit?): List<Todo> {
        return todoRepository.getTodos()
    }
}