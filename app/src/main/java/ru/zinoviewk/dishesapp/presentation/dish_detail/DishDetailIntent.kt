package ru.zinoviewk.dishesapp.presentation.dish_detail

import ru.zinoviewk.dishesapp.presentation.core.ViewIntent

sealed class DishDetailIntent : ViewIntent {

    class LoadDish(val id: String) : DishDetailIntent()
}