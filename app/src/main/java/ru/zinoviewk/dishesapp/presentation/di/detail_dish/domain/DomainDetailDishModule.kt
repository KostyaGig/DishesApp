package ru.zinoviewk.dishesapp.presentation.di.detail_dish.domain

import dagger.Binds
import dagger.Module
import ru.zinoviewk.dishesapp.domain.dishes.DishesUseCase
import ru.zinoviewk.dishesapp.domain.dishes.DishesUseCaseImpl
import ru.zinoviewk.dishesapp.presentation.di.detail_dish.DetailDishFeature
import ru.zinoviewk.dishesapp.presentation.di.dishes.DishesFeature

@Module
abstract class DomainDetailDishModule {

    @DetailDishFeature
    @Binds
    abstract fun bindDishesUseCase(useCase: DishesUseCaseImpl): DishesUseCase
}