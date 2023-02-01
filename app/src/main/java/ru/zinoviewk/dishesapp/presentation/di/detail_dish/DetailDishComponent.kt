package ru.zinoviewk.dishesapp.presentation.di.detail_dish

import dagger.Component
import ru.zinoviewk.dishesapp.presentation.di.core.AppComponent
import ru.zinoviewk.dishesapp.presentation.di.detail_dish.domain.DomainDetailDishModule
import ru.zinoviewk.dishesapp.presentation.dish_detail.DishDetailFragment
import javax.inject.Scope

@DetailDishFeature
@Component(
    modules = [DomainDetailDishModule::class],
    dependencies = [AppComponent::class]
)
interface DetailDishComponent {

    fun inject(fragment: DishDetailFragment)
}

@Scope
annotation class DetailDishFeature