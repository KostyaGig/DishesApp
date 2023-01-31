package ru.zinoviewk.dishesapp.presentation.dishes

import ru.zinoviewk.dishesapp.presentation.core.ViewState

sealed class DishesState : ViewState{

    object Empty : DishesState()
}
