package ru.zinoviewk.dishesapp.domain.dishes

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.zinoviewk.dishesapp.data.DishesRepository
import ru.zinoviewk.dishesapp.domain.dishes.mapper.toDomainDish
import javax.inject.Inject

interface DishesUseCase {

    suspend operator fun invoke() : Flow<List<DomainDish>>
}

class DishesUseCaseImpl @Inject constructor(
    private val dishesRepository: DishesRepository
) : DishesUseCase{

    override suspend fun invoke() = dishesRepository.fetchDishes().map { dataDishes ->
        dataDishes.map { dataDish ->
            dataDish.toDomainDish()
        }
    }
}