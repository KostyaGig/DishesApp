package ru.zinoviewk.dishesapp.data.cache

import kotlinx.coroutines.flow.Flow
import ru.zinoviewk.dishesapp.data.DataDish
import ru.zinoviewk.dishesapp.data.core.DishesDataSource
import ru.zinoviewk.dishesapp.data.mapper.toCacheDish

interface CacheDataSource : DishesDataSource<Flow<List<CacheDish>>> {

    fun isEmpty(): Boolean

    suspend fun fetchDish(id: String): Flow<CacheDish>

    suspend fun saveDishes(dishes: List<DataDish>)

    suspend fun deleteDishes(ids: List<String>)
    suspend fun deleteAllDishes()
}

class CacheDataSourceImpl(
    private val dao: DishesDao
) : CacheDataSource {
    override fun isEmpty() = dao.fetchListOfDishes().isEmpty()

    override suspend fun fetchDishes(): Flow<List<CacheDish>> = dao.fetchDishes()
    override suspend fun fetchDish(id: String) = dao.fetchDish(id)

    override suspend fun saveDishes(dishes: List<DataDish>) = dao.insertDishes(
        dishes.map { dataDish -> dataDish.toCacheDish() }
    )

    override suspend fun deleteDishes(ids: List<String>) = dao.deleteDishes(ids)
    override suspend fun deleteAllDishes() = dao.deleteAllDishes()
}