package ru.zinoviewk.dishesapp.data.cloud

data class CloudDish(
    val id: String,
    val name: String,
    val description: String?,
    val image: String?,
    val price: Int,
)