package ru.zinoviewk.dishesapp.presentation.dishes

import ru.zinoviewk.dishesapp.R
import ru.zinoviewk.dishesapp.core.ResourceProvider
import ru.zinoviewk.dishesapp.domain.dishes.DomainDish
import ru.zinoviewk.dishesapp.presentation.dishes.recycler_view.DishRecyclerItem

fun DomainDish.toDishItem(resourceProvider: ResourceProvider) = DishRecyclerItem.Base(
    id = id,
    title = name,
    shortDescription = shortDescription,
    url = image,
    price = resourceProvider.getString(R.string.price_text, price),
    isCheckedToDelete = false
)