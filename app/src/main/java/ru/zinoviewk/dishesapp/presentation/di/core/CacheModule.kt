package ru.zinoviewk.dishesapp.presentation.di.core

import android.content.Context
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.zinoviewk.dishesapp.data.cache.*
import ru.zinoviewk.dishesapp.data.cache.DishesDatabase.Companion.DATABASE_NAME
import javax.inject.Singleton

@Module
class CacheModule {

    @Singleton
    @Provides
    fun provideDishesDataBase(@AppContext context: Context): DishesDatabase {
        return Room.databaseBuilder(
            context,
            DishesDatabase::class.java, DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideDishesDao(db: DishesDatabase): DishesDao {
        return db.dishesDao()
    }

    @Singleton
    @Provides
    fun provideCacheDataSourceDataSource(dao: DishesDao): CacheDataSource {
        return CacheDataSourceImpl(dao)
    }
}