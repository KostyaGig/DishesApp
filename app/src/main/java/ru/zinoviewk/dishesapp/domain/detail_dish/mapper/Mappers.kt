package ru.zinoviewk.dishesapp.domain.detail_dish.mapper

import ru.zinoviewk.dishesapp.data.DataDish
import ru.zinoviewk.dishesapp.domain.detail_dish.DomainDetailDish
import ru.zinoviewk.dishesapp.domain.dishes.DomainDish

fun DataDish.toDomainDetailDish() = DomainDetailDish(
    id = id,
    name = name,
    description = description,
    image = image,
    price = price
)
