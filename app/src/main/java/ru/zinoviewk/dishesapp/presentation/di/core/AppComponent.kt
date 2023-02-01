package ru.zinoviewk.dishesapp.presentation.di.core

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.Provides
import ru.zinoviewk.dishesapp.core.NetworkConnection
import ru.zinoviewk.dishesapp.core.ResourceProvider
import ru.zinoviewk.dishesapp.data.DishesRepository
import javax.inject.Scope
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class])
interface AppComponent {

    @AppContext fun provideContext() : Context
    fun provideDishesRepository() : DishesRepository
    fun provideResourceProvider() : ResourceProvider
    fun networkConnection() : NetworkConnection

    @Component.Builder
    interface Builder {
        @BindsInstance fun context(@AppContext context: Context) : Builder
        @BindsInstance fun resourceProvider(resourceProvider: ResourceProvider) : Builder
        fun build(): AppComponent
    }
}

@Scope
annotation class AppContext


