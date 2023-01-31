package ru.zinoviewk.dishesapp.domain.dishes.mapper

import ru.zinoviewk.dishesapp.data.DataDish
import ru.zinoviewk.dishesapp.data.cache.CacheDish
import ru.zinoviewk.dishesapp.data.cloud.CloudDish
import ru.zinoviewk.dishesapp.domain.dishes.DomainDish

private const val MAX_DESC_LENGTH = 22

fun DataDish.toDomainDish() = DomainDish(
    id = id,
    name = name,
    shortDescription = subDescriptionIfNeed(description),
    image = image,
    price = price
)

private fun subDescriptionIfNeed(desc: String) : String {
    return if (desc.length > MAX_DESC_LENGTH) {
        desc.substring(0, MAX_DESC_LENGTH) + "..."
    } else desc
}
