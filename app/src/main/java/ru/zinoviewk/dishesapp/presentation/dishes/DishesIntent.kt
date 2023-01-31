package ru.zinoviewk.dishesapp.presentation.dishes

import ru.zinoviewk.dishesapp.presentation.core.ViewIntent

sealed class DishesIntent : ViewIntent {

    object Empty : DishesIntent()
}