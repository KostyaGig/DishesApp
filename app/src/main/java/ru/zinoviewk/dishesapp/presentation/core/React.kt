package ru.zinoviewk.dishesapp.presentation.core

interface React<EVENT : ViewEvent> {

    fun react(event: EVENT)
}