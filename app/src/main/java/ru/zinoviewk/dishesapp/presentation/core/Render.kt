package ru.zinoviewk.dishesapp.presentation.core

interface Render<STATE : ViewState> {

    fun render(state: STATE)
}