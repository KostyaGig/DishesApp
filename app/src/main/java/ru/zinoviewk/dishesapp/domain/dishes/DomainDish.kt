package ru.zinoviewk.dishesapp.domain.dishes

data class DomainDish(
    val id: String,
    val name: String,
    val shortDescription: String,
    val image: String,
    val price: Int,
)
