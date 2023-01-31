package ru.zinoviewk.dishesapp.data.mapper

import ru.zinoviewk.dishesapp.data.DataDish
import ru.zinoviewk.dishesapp.data.cache.CacheDish
import ru.zinoviewk.dishesapp.data.cloud.CloudDish

fun CloudDish.toDataDish() = DataDish(
    id = id,
    name = name,
    description =description ?: "",
    image = image ?: "",
    price
)

fun DataDish.toCacheDish() = CacheDish(
    id = id,
    name = name,
    description = description,
    image = image,
    price =price
)

fun CacheDish.toDataDish() = DataDish(
    id = id,
    name = name,
    description = description,
    image = image,
    price =price
)