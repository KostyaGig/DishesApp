package ru.zinoviewk.dishesapp.presentation.core

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel<STATE : ViewState, INTENT : ViewIntent, ACTION : ViewAction>(
    private val defaultState: STATE
) : Model<STATE, INTENT>, ViewModel() {

    protected val _state = MutableStateFlow(defaultState)
    override val state = _state.asStateFlow()

    override fun dispatchIntent(intent: INTENT) = handleAction(intentToAction(intent))

    abstract fun intentToAction(intent: INTENT): ACTION

    abstract fun handleAction(action: ACTION)
}