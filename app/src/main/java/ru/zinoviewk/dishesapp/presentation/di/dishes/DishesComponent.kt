package ru.zinoviewk.dishesapp.presentation.di.dishes

import dagger.Component
import ru.zinoviewk.dishesapp.core.NetworkConnection
import ru.zinoviewk.dishesapp.presentation.di.core.AppComponent
import ru.zinoviewk.dishesapp.presentation.di.dishes.domain.DomainDishesModule
import ru.zinoviewk.dishesapp.presentation.di.dishes.ui.UiDishesModule
import ru.zinoviewk.dishesapp.presentation.dishes.DishesFragment
import javax.inject.Scope

@DishesFeature
@Component(
    modules = [DomainDishesModule::class, UiDishesModule::class],
    dependencies = [AppComponent::class]
)
interface DishesComponent {
    fun inject(fragment: DishesFragment)
}

@Scope
annotation class DishesFeature