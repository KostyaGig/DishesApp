package ru.zinoviewk.dishesapp.domain.dishes

import ru.zinoviewk.dishesapp.data.DishesRepository
import javax.inject.Inject

interface DeleteDishUseCase {

    suspend operator fun invoke(ids: List<String>)
}

class DeleteDishUseCaseImpl @Inject constructor(
    private val repository: DishesRepository
) : DeleteDishUseCase {

    override suspend fun invoke(ids: List<String>) = repository.deleteDishes(ids)
}