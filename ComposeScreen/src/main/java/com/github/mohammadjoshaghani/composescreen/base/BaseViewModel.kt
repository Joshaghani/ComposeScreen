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
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<Event : ViewEvent, UiState : ViewState<Event>, Effect : ViewSideEffect> :
    ViewModel() {

    open fun initViewModel() {}
    abstract fun setInitialState(): UiState
    abstract fun handleEvents(event: Event)

    private val initialState: UiState by lazy { setInitialState() }

    private val _viewState: MutableState<UiState> = mutableStateOf(initialState)

    private val event: MutableSharedFlow<Event> = MutableSharedFlow()

    private var _effect: Channel<Effect> = Channel()

    val viewState: State<UiState> = _viewState

    protected val state: UiState = viewState.value

    var effect = _effect.receiveAsFlow()

    fun launchOnScope(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch { block() }
    }

    init {
        subscribeToEvents()
    }

    private fun subscribeToEvents() {
        launchOnScope {
            event.collect { handleEvents(it) }
        }
    }

    fun setEvent(event: Event) {
        launchOnScope { this@BaseViewModel.event.emit(event) }
    }

    fun setState(reducer: UiState.() -> UiState) {
        val newState = viewState.value.reducer()
        _viewState.value = newState
    }

    fun setEffect(builder: () -> Effect) {
        val effectValue = builder()
        launchOnScope { _effect.send(effectValue) }
    }

}