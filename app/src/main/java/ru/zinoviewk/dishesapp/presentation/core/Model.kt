package ru.zinoviewk.dishesapp.presentation.core

import kotlinx.coroutines.flow.StateFlow

interface Model<STATE : ViewState, INTENT : ViewIntent> {

    val state: StateFlow<STATE>

    fun dispatchIntent(intent: INTENT)
}