package ru.zinoviewk.dishesapp.presentation.dishes

import ru.zinoviewk.dishesapp.domain.dishes.DomainDish
import ru.zinoviewk.dishesapp.presentation.dishes.recycler_view.DishRecyclerItem

fun DomainDish.toDishItem() = DishRecyclerItem.Base(
    id = id,
    title = name,
    shortDescription = shortDescription,
    url = image,
    price = price.toString(),
    isCheckedToDelete = false
)