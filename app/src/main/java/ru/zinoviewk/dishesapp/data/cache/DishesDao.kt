package ru.zinoviewk.dishesapp.data.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DishesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDishes(dishes: List<CacheDish>)

    @Query("select * from Dishes")
    fun fetchDishes(): Flow<List<CacheDish>>

    @Query("select * from Dishes")
    fun fetchListOfDishes(): List<CacheDish>

    @Query("select * from Dishes where dishId like :id")
    fun fetchDish(id: String): Flow<CacheDish>

    @Query("delete from Dishes where dishId like :id")
    suspend fun deleteDish(id: String)
}