package ru.zinoviewk.dishesapp.presentation.dish_detail

import ru.zinoviewk.dishesapp.domain.detail_dish.DomainDetailDish

fun DomainDetailDish.toDetailDishItem() = DishDetailItem(
    id = id,
    title = name,
    fullDescription = description,
    url = image,
    price = price.toString()
)