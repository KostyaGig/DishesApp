package ru.zinoviewk.dishesapp.presentation.dish_detail

import ru.zinoviewk.dishesapp.presentation.core.ViewAction

sealed class DishDetailAction : ViewAction {

    class FetchDish(val id: String) : DishDetailAction()
}