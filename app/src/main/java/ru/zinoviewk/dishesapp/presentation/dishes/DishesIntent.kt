package ru.zinoviewk.dishesapp.presentation.dishes

import ru.zinoviewk.dishesapp.presentation.core.ViewIntent

sealed class DishesIntent : ViewIntent {

    object LoadAllDishes : DishesIntent()

    class SelectToRemove(val id: String): DishesIntent()

    object RemoveSelected : DishesIntent()

    object RemoveAll : DishesIntent()

    class ShowDetail(val id: String) : DishesIntent()
}