package ru.zinoviewk.dishesapp.presentation.di.dishes.domain

import dagger.Binds
import dagger.Module
import ru.zinoviewk.dishesapp.domain.dishes.DeleteDishUseCase
import ru.zinoviewk.dishesapp.domain.dishes.DeleteDishUseCaseImpl
import ru.zinoviewk.dishesapp.domain.dishes.DishesUseCase
import ru.zinoviewk.dishesapp.domain.dishes.DishesUseCaseImpl
import ru.zinoviewk.dishesapp.presentation.di.dishes.DishesFeature

@Module
abstract class DomainDishesModule {

    @DishesFeature
    @Binds
    abstract fun bindDishesUseCase(useCase: DishesUseCaseImpl): DishesUseCase

    @DishesFeature
    @Binds
    abstract fun bindDeleteDishUseCase(useCase: DeleteDishUseCaseImpl): DeleteDishUseCase
}