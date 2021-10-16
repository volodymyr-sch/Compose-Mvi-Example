package com.vsch.mvi.presentation.main

import androidx.lifecycle.viewModelScope
import com.vsch.mvi.base.BaseViewModel
import com.vsch.mvi.base.Reducer
import com.vsch.mvi.base.TimeCapsule
import com.vsch.mvi.domain.use_case.IGetTodosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getTodos: IGetTodosUseCase,
    private val dispatcher: CoroutineDispatcher,
    private val viewMapper: MainScreenViewDataMapper,
) : BaseViewModel<MainScreenState, MainScreenUiEvent>() {

    private val reducer = MainReducer(MainScreenState.initial())

    override val state: StateFlow<MainScreenState>
        get() = reducer.state

    val timeMachine: TimeCapsule<MainScreenState>
        get() = reducer.timeCapsule

    init {
        viewModelScope.launch(dispatcher) {
            val data = getTodos.invoke()
            sendEvent(MainScreenUiEvent.ShowData(viewMapper.buildScreen(data)))
        }
    }

    private fun sendEvent(event: MainScreenUiEvent) {
        reducer.sendEvent(event)
    }

    fun changeAddDialogState(show: Boolean) {
        sendEvent(MainScreenUiEvent.OnChangeDialogState(show))
    }

    fun addNewItem(text: String) {
        sendEvent(MainScreenUiEvent.AddNewItem(text))
    }

    fun onItemCheckedChanged(index: Int, isChecked: Boolean) {
        sendEvent(MainScreenUiEvent.OnItemCheckedChanged(index, isChecked))
    }

    private class MainReducer(initial: MainScreenState) : Reducer<MainScreenState, MainScreenUiEvent>(initial) {
        override fun reduce(oldState: MainScreenState, event: MainScreenUiEvent) {
            when (event) {
                is MainScreenUiEvent.OnChangeDialogState -> {
                    setState(oldState.copy(isShowAddDialog = event.show))
                }
                is MainScreenUiEvent.ShowData -> {
                    setState(oldState.copy(isLoading = false, data = event.items))
                }
                is MainScreenUiEvent.DismissDialog -> {
                    setState(oldState.copy(isShowAddDialog = false))
                }
                is MainScreenUiEvent.AddNewItem -> {
                    val newList = oldState.data.toMutableList()
                    newList.add(
                        index = oldState.data.size - 1,
                        element = MainScreenItem.MainScreenTodoItem(false, event.text),
                    )
                    setState(oldState.copy(
                        data = newList,
                        isShowAddDialog = false
                    ))
                }
                is MainScreenUiEvent.OnItemCheckedChanged -> {
                    val newList = oldState.data.toMutableList()
                    newList[event.index] = (newList[event.index] as MainScreenItem.MainScreenTodoItem).copy(isChecked = event.isChecked)
                    setState(oldState.copy(data = newList))
                }
            }
        }
    }
}