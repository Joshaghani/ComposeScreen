package com.github.mohammadjoshaghani.composescreen.base

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewEvent
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewSideEffect
import com.github.mohammadjoshaghani.composescreen.base.contract.ViewState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<
        Event : ViewEvent,
        UiState : ViewState<Event>,
        Effect : ViewSideEffect
        > : ViewModel() {

    // ---------------------
    // State
    // ---------------------
    private val initialState: UiState by lazy { setInitialState() }
    private val _viewState: MutableState<UiState> = mutableStateOf(initialState)
    val viewState: State<UiState> = _viewState

    fun setState(reducer: UiState.() -> UiState) {
        _viewState.value = _viewState.value.reducer()
    }

    abstract fun setInitialState(): UiState

    // ---------------------
    // Event
    // ---------------------
    private val _event = MutableSharedFlow<Event>(extraBufferCapacity = 1)
    val events = _event.asSharedFlow()

    fun setEvent(event: Event) {
        _event.tryEmit(event) // Non-suspending emit
    }

    abstract fun handleEvents(event: Event)

    private fun subscribeToEvents() {
        viewModelScope.launch {
            _event.collect { handleEvents(it) }
        }
    }

    // ---------------------
    // Side Effect
    // ---------------------
    private val _effect = MutableSharedFlow<Effect>(replay = 0, extraBufferCapacity = 1)
    val effect = _effect.asSharedFlow()

    fun setEffect(builder: () -> Effect) {
        _effect.tryEmit(builder())
    }

    // ---------------------
    // Lifecycle / init
    // ---------------------
    open fun initViewModel() {}

    init {
        subscribeToEvents()
        initViewModel()
    }

}