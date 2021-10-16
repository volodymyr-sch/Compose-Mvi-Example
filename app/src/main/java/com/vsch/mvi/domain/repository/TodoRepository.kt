package com.vsch.mvi.domain.repository

import com.vsch.mvi.data.TodoDataSource
import com.vsch.mvi.domain.entities.Todo
import com.vsch.mvi.domain.mapper.TodoMapper
import javax.inject.Inject

interface ITodoRepository {
    suspend fun getTodos(): List<Todo>
}

class TodoRepository @Inject constructor(
    private val todoMapper: TodoMapper,
    private val todoDataSource: TodoDataSource
): ITodoRepository {

    override suspend fun getTodos(): List<Todo> {
        return todoDataSource.getTodos().map(todoMapper::mapFromLocal)
    }

}