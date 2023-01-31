package ru.zinoviewk.dishesapp.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import ru.zinoviewk.dishesapp.data.cache.CacheDataSource
import ru.zinoviewk.dishesapp.data.cloud.CloudDataSource
import ru.zinoviewk.dishesapp.data.mapper.toDataDish
import javax.inject.Inject

interface DishesRepository {

    suspend fun fetchDishes(): Flow<List<DataDish>>

    suspend fun fetchDish(id: String): Flow<DataDish>

    suspend fun deleteDish(id: String)
}

class DishesRepositoryImpl @Inject constructor(
    private val cloudDataSource: CloudDataSource,
    private val cacheDataSource: CacheDataSource
) : DishesRepository {

    override suspend fun fetchDishes(): Flow<List<DataDish>> {
        return if (cacheDataSource.isEmpty()) {
            val dataDishes = cloudDataSource.fetchDishes().map { cloudDish ->
                cloudDish.toDataDish()
            }
            saveToCache(dataDishes)
            flow { emit(dataDishes) }
        } else {
            // todo rename
            cacheDataSource.fetchCachedDishes().map { it -> it.map { it.toDataDish() } }
        }
    }

    private suspend fun saveToCache(dishes: List<DataDish>) = cacheDataSource.saveDishes(dishes)

    override suspend fun fetchDish(id: String): Flow<DataDish> {
        return if (cacheDataSource.isEmpty()) {
            cacheDataSource.fetchDish(id).map { cacheDish -> cacheDish.toDataDish() }
        } else {
            return flow { emit(cloudDataSource.fetchDish(id).toDataDish()) }
        }
    }

    override suspend fun deleteDish(id: String) = cacheDataSource.deleteDish(id)
}
