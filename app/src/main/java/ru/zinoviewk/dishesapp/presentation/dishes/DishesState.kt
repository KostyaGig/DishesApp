package ru.zinoviewk.dishesapp.presentation.dishes

import ru.zinoviewk.dishesapp.presentation.core.ViewState
import ru.zinoviewk.dishesapp.presentation.dishes.recycler_view.DishRecyclerItem

sealed class DishesState : ViewState {

    open fun copy(newDishes: List<DishRecyclerItem>): DishesState = Empty

    object Empty : DishesState()

    object Progress : DishesState()

    class Dishes(val dishes: List<DishRecyclerItem>) : DishesState() {

        override fun copy(newDishes: List<DishRecyclerItem>): DishesState = Dishes(dishes = newDishes)
    }

    class Error(val message: String) : DishesState()
}
