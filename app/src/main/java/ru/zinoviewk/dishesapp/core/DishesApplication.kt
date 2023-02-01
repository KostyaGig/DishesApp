package ru.zinoviewk.dishesapp.core

import android.app.Application
import android.content.Context
import ru.zinoviewk.dishesapp.presentation.di.core.AppComponent
import ru.zinoviewk.dishesapp.presentation.di.core.DaggerAppComponent

class DishesApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .builder()
            .context(this)
            .resourceProvider(ResourceProviderImpl(this))
            .build()
    }

}

val Context.appComponent: AppComponent
    get() = when (this) {
        is DishesApplication -> appComponent
        else -> this.applicationContext.appComponent
    }
