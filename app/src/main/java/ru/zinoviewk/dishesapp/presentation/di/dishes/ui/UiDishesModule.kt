package ru.zinoviewk.dishesapp.presentation.di.dishes.ui

import dagger.Module
import dagger.Provides
import ru.zinoviewk.dishesapp.domain.dishes.DeleteDishUseCase
import ru.zinoviewk.dishesapp.domain.dishes.DishesUseCase
import ru.zinoviewk.dishesapp.presentation.di.detail_dish.DetailDishFeature
import ru.zinoviewk.dishesapp.presentation.di.dishes.DishesFeature
import ru.zinoviewk.dishesapp.presentation.dishes.DishesViewModelFactory

@Module
class UiDishesModule {

    @DishesFeature
    @Provides
    fun provideDishesViewModelFactory(
        dishesUseCase: DishesUseCase,
        deleteDishUseCase: DeleteDishUseCase
    ) : DishesViewModelFactory{
        return DishesViewModelFactory()
    }
}