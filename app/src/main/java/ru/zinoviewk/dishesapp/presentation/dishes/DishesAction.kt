package ru.zinoviewk.dishesapp.presentation.dishes

import ru.zinoviewk.dishesapp.presentation.core.ViewAction

sealed class DishesAction : ViewAction {

    object Empty : DishesAction()
}