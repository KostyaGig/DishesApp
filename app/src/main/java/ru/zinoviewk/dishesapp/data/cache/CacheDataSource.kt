package ru.zinoviewk.dishesapp.data.cache

import kotlinx.coroutines.flow.Flow
import ru.zinoviewk.dishesapp.data.DataDish
import ru.zinoviewk.dishesapp.data.mapper.toCacheDish

interface CacheDataSource {

    fun isEmpty(): Boolean

    // todo move these two methods to interface
    suspend fun fetchCachedDishes(): Flow<List<CacheDish>>

    suspend fun fetchDish(id: String): Flow<CacheDish>

    suspend fun saveDishes(dishes: List<DataDish>)

    suspend fun deleteDish(id: String)
}

class CacheDataSourceImpl(
    private val dao: DishesDao
) : CacheDataSource {
    override fun isEmpty() = dao.fetchListOfDishes().isEmpty()

    override suspend fun fetchCachedDishes() = dao.fetchDishes()

    override suspend fun fetchDish(id: String) = dao.fetchDish(id)

    override suspend fun saveDishes(dishes: List<DataDish>) = dao.insertDishes(
        dishes.map { dataDish -> dataDish.toCacheDish() }
    )

    override suspend fun deleteDish(id: String) = dao.deleteDish(id)
}