package ru.zinoviewk.dishesapp.domain.dishes

import ru.zinoviewk.dishesapp.data.DishesRepository
import javax.inject.Inject

interface DeleteAllDishesUseCase {

    suspend operator fun invoke()
}

class DeleteAllDishesUseCaseImpl @Inject constructor(
    private val repository: DishesRepository
) : DeleteAllDishesUseCase {

    override suspend fun invoke() = repository.deleteAllDishes()
}