package ru.zinoviewk.dishesapp.presentation.dish_detail

import ru.zinoviewk.dishesapp.presentation.core.ViewState

sealed class DishDetailState : ViewState {

    object Progress : DishDetailState()

    class Dish(val dishItem: DishDetailItem) : DishDetailState()
}
