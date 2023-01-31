package ru.zinoviewk.dishesapp.presentation.di.core

import dagger.Binds
import dagger.Module
import ru.zinoviewk.dishesapp.data.DishesRepository
import ru.zinoviewk.dishesapp.data.DishesRepositoryImpl
import javax.inject.Singleton

@Module(includes = [CloudModule::class, CacheModule::class])
abstract class DataModule {

    @Singleton
    @Binds
    abstract fun bindDishesRepository(repository: DishesRepositoryImpl) : DishesRepository
}