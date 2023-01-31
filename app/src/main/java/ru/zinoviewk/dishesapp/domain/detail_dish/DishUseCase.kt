package ru.zinoviewk.dishesapp.domain.detail_dish

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.zinoviewk.dishesapp.data.DishesRepository
import ru.zinoviewk.dishesapp.domain.detail_dish.mapper.toDomainDetailDish
import javax.inject.Inject

interface DishUseCase {

    suspend operator fun invoke(id: String): Flow<DomainDetailDish>
}

class DishUseCaseImpl @Inject constructor(
    private val dishesRepository: DishesRepository
) : DishUseCase {

    override suspend fun invoke(id: String): Flow<DomainDetailDish> {
        return dishesRepository.fetchDish(id).map { dish -> dish.toDomainDetailDish() }
    }
}