package ru.zinoviewk.dishesapp.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import ru.zinoviewk.dishesapp.data.cache.CacheDataSource
import ru.zinoviewk.dishesapp.data.cloud.CloudDataSource
import ru.zinoviewk.dishesapp.data.mapper.toDataDish
import javax.inject.Inject

interface DishesRepository {

    suspend fun fetchDishesFromNetworkAndSave(): Flow<List<DataDish>>

    suspend fun fetchDishesFromCache(): Flow<List<DataDish>>

    suspend fun fetchDish(id: String): Flow<DataDish>

    suspend fun deleteDishes(ids: List<String>)

    suspend fun deleteAllDishes()
}

class DishesRepositoryImpl @Inject constructor(
    private val cloudDataSource: CloudDataSource,
    private val cacheDataSource: CacheDataSource
) : DishesRepository {

    override suspend fun fetchDishesFromNetworkAndSave(): Flow<List<DataDish>> {
        val dataDishes = cloudDataSource.fetchDishes().map { cloudDish -> cloudDish.toDataDish() }
        saveToCache(dataDishes)
        return fetchDishesFromCache()
    }

    private suspend fun saveToCache(dishes: List<DataDish>) = cacheDataSource.saveDishes(dishes)

    override suspend fun fetchDishesFromCache(): Flow<List<DataDish>> {
        return cacheDataSource.fetchDishes().map { cacheDishes ->
            cacheDishes.map { cacheDish -> cacheDish.toDataDish() }
        }
    }

    override suspend fun fetchDish(id: String): Flow<DataDish> {
        return if (!cacheDataSource.isEmpty()) {
            cacheDataSource.fetchDish(id).map { cacheDish -> cacheDish.toDataDish() }
        } else {
            return flow { emit(cloudDataSource.fetchDish(id).toDataDish()) }
        }
    }

    override suspend fun deleteDishes(ids: List<String>) = cacheDataSource.deleteDishes(ids)
    override suspend fun deleteAllDishes() = cacheDataSource.deleteAllDishes()
}
