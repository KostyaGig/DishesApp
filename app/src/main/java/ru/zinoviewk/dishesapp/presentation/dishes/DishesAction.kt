package ru.zinoviewk.dishesapp.presentation.dishes

import ru.zinoviewk.dishesapp.presentation.core.ViewAction

sealed class DishesAction : ViewAction {

    object FetchDishes : DishesAction()

    class SelectToDelete(val id: String) : DishesAction()

    class ShowDetail(val id: String) : DishesAction()

    object DeleteSelected : DishesAction()

    object DeleteAll : DishesAction()
}