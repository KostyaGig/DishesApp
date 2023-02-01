package ru.zinoviewk.dishesapp.presentation.dish_detail

import ru.zinoviewk.dishesapp.domain.detail_dish.DomainDetailDishResult

fun DomainDetailDishResult.reduce(): DishDetailState {
    return when(this) {
        is DomainDetailDishResult.Progress -> DishDetailState.Progress
        is DomainDetailDishResult.DishDetail -> DishDetailState.Dish(this.dish.toDetailDishItem())
    }
}

