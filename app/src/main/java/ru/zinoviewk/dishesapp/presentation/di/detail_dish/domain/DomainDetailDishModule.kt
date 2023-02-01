package ru.zinoviewk.dishesapp.presentation.di.detail_dish.domain

import dagger.Binds
import dagger.Module
import ru.zinoviewk.dishesapp.domain.detail_dish.DishDetailUseCase
import ru.zinoviewk.dishesapp.domain.detail_dish.DishDetailUseCaseImpl
import ru.zinoviewk.dishesapp.presentation.di.detail_dish.DetailDishFeature

@Module
abstract class DomainDetailDishModule {

    @DetailDishFeature
    @Binds
    abstract fun bindDishDetailUseCase(useCase: DishDetailUseCaseImpl): DishDetailUseCase
}