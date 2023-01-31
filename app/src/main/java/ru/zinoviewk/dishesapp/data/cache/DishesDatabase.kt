package ru.zinoviewk.dishesapp.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.zinoviewk.dishesapp.data.cache.DishesDatabase.Companion.DATABASE_VERSION

@Database(entities = [CacheDish::class], version = DATABASE_VERSION)
abstract class DishesDatabase : RoomDatabase() {

    abstract fun dishesDao(): DishesDao

    companion object {
        const val DATABASE_NAME = "Dishes_db"
        const val DATABASE_VERSION = 1
    }
}