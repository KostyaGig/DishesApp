package ru.zinoviewk.dishesapp.presentation.dish_detail

import ru.zinoviewk.dishesapp.R
import ru.zinoviewk.dishesapp.core.ResourceProvider
import ru.zinoviewk.dishesapp.domain.detail_dish.DomainDetailDish

fun DomainDetailDish.toDetailDishItem(resourceProvider: ResourceProvider) = DishDetailItem(
    id = id,
    title = name,
    fullDescription = description,
    url = image,
    price = resourceProvider.getString(R.string.price_text, price)
)