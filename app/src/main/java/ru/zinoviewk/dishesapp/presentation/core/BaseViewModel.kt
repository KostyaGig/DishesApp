package ru.zinoviewk.dishesapp.presentation.core

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*

abstract class BaseViewModel<STATE : ViewState, INTENT : ViewIntent, ACTION : ViewAction, EVENT : ViewEvent>(
    defaultState: STATE
) : Model<STATE, INTENT, EVENT>, ViewModel() {

    protected val _state = MutableStateFlow(defaultState)
    override val state = _state.asStateFlow()

    protected val _event = MutableSharedFlow<EVENT>(replay = 0, extraBufferCapacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    override val event: SharedFlow<EVENT> = _event.asSharedFlow()

    override fun dispatchIntent(intent: INTENT) = handleAction(intentToAction(intent))

    abstract fun intentToAction(intent: INTENT): ACTION

    abstract fun handleAction(action: ACTION)
}