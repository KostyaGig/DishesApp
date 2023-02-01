package ru.zinoviewk.dishesapp.data.core

interface DishesDataSource<T> {

    suspend fun fetchDishes() : T
}