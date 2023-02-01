package ru.zinoviewk.dishesapp.presentation.di.dishes.domain

import dagger.Binds
import dagger.Module
import ru.zinoviewk.dishesapp.domain.dishes.*
import ru.zinoviewk.dishesapp.presentation.di.core.AppContext
import ru.zinoviewk.dishesapp.presentation.di.dishes.DishesFeature

@Module
abstract class DomainDishesModule {

    @DishesFeature
    @Binds
    abstract fun bindDishesUseCase(useCase: DishesUseCaseImpl): DishesUseCase

    @DishesFeature
    @Binds
    abstract fun bindDeleteDishUseCase(useCase: DeleteDishUseCaseImpl): DeleteDishUseCase

    @DishesFeature
    @Binds
    abstract fun bindDeleteAllDishesUseCase(useCase: DeleteAllDishesUseCaseImpl): DeleteAllDishesUseCase
}