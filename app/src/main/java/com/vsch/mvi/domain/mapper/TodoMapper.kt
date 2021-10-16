package com.vsch.mvi.domain.mapper

import com.vsch.mvi.data.model.TodoLocal
import com.vsch.mvi.domain.entities.Todo
import java.util.*
import javax.inject.Inject

class TodoMapper @Inject constructor() {

    fun mapToLocal(todo: Todo) = TodoLocal().apply {
        id = UUID.randomUUID().toString()
        text = todo.text
        isChecked = todo.isChecked
    }

    fun mapFromLocal(todoLocal: TodoLocal) = Todo(
        text = todoLocal.text,
        isChecked = todoLocal.isChecked
    )
}