package ru.zinoviewk.dishesapp.domain.dishes

import kotlinx.coroutines.flow.*
import ru.zinoviewk.dishesapp.core.NetworkConnection
import ru.zinoviewk.dishesapp.data.DishesRepository
import ru.zinoviewk.dishesapp.domain.dishes.mapper.toDomainDish
import ru.zinoviewk.dishesapp.domain.randomSleep
import javax.inject.Inject

interface DishesUseCase {

    suspend operator fun invoke(): Flow<DishesDomainResult>
}

class DishesUseCaseImpl @Inject constructor(
    private val dishesRepository: DishesRepository,
    private val networkConnection: NetworkConnection
) : DishesUseCase {

    override suspend fun invoke() = flow {
        emit(DishesDomainResult.Loading)
        randomSleep()
        if (networkConnection.isAvailable()) {
            dishesRepository
                .fetchDishesFromNetworkAndSave()
                .map { dataDishes -> dataDishes.map { dataDish -> dataDish.toDomainDish() } }
                .onEach { dishes ->
                    if (dishes.isEmpty())
                        emit(DishesDomainResult.EmptyDishes)
                    else
                        emit(DishesDomainResult.Dishes(dishes))
                }.collect()
        } else {
            dishesRepository
                .fetchDishesFromCache()
                .map { dataDishes -> dataDishes.map { dataDish -> dataDish.toDomainDish() } }
                .onEach { dataDishes ->
                    if (dataDishes.isEmpty())
                        emit(DishesDomainResult.NoConnection)
                    else
                        emit(DishesDomainResult.Dishes(dataDishes))
                }.collect()
        }
    }


}