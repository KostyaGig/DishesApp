package ru.zinoviewk.dishesapp.domain.detail_dish

import kotlinx.coroutines.flow.*
import ru.zinoviewk.dishesapp.data.DishesRepository
import ru.zinoviewk.dishesapp.domain.detail_dish.mapper.toDomainDetailDish
import ru.zinoviewk.dishesapp.domain.randomSleep
import javax.inject.Inject

interface DishDetailUseCase {

    suspend operator fun invoke(id: String): Flow<DomainDetailDishResult>
}

class DishDetailUseCaseImpl @Inject constructor(
    private val dishesRepository: DishesRepository
) : DishDetailUseCase {

    override suspend fun invoke(id: String): Flow<DomainDetailDishResult> = flow {
        emit(DomainDetailDishResult.Progress)
        randomSleep()
        dishesRepository
            .fetchDish(id)
            .map { dish -> dish.toDomainDetailDish() }
            .onEach { dish -> emit(DomainDetailDishResult.DishDetail(dish)) }
            .collect {}
    }
}