package ru.zinoviewk.dishesapp.presentation.dishes

import ru.zinoviewk.dishesapp.presentation.core.ViewEvent

sealed class DishesEvent : ViewEvent {

    class OpenDish(val id: String) : DishesEvent()
}
