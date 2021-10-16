package com.vsch.mvi.presentation.main

sealed class MainScreenItem {

    object MainScreenAddButtonItem : MainScreenItem()

    data class MainScreenTodoItem(
        val isChecked: Boolean,
        val text: String,
    ) : MainScreenItem()

}
