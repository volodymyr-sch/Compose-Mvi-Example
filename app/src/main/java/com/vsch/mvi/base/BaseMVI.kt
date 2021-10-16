package com.vsch.mvi.base

import com.vsch.mvi.BuildConfig
import kotlinx.coroutines.flow.*

abstract class Reducer<S : UiState, E : UiEvent>(initialVal: S) {

    private val _state: MutableStateFlow<S> = MutableStateFlow(initialVal)
    val state: StateFlow<S>
        get() = _state

    val timeCapsule: TimeCapsule<S> = TimeTravelCapsule { storedState ->
        _state.tryEmit(storedState)
    }

    init {
        timeCapsule.addState(initialVal)
    }

    fun sendEvent(event: E) {
        reduce(_state.value, event)
    }

    fun setState(newState: S) {
        val success = _state.tryEmit(newState)

        if (BuildConfig.DEBUG && success) {
            timeCapsule.addState(newState)
        }
    }

    abstract fun reduce(oldState: S, event: E)
}

interface UiState

interface UiEvent
