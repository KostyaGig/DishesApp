package ru.zinoviewk.dishesapp.presentation.di.core

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.Provides
import ru.zinoviewk.dishesapp.data.DishesRepository
import javax.inject.Scope
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class])
interface AppComponent {

    @AppContext fun provideContext() : Context
    fun provideDishesRepository() : DishesRepository

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(@AppContext context: Context) : Builder
        fun build(): AppComponent
    }
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class AppContext


