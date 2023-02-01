package ru.zinoviewk.dishesapp.presentation.core

import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface Model<STATE : ViewState, INTENT : ViewIntent, EVENT : ViewEvent> {

    val state: StateFlow<STATE>
    val event: SharedFlow<EVENT>

    fun dispatchIntent(intent: INTENT)
}