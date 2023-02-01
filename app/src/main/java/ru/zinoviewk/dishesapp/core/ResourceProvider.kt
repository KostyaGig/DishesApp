package ru.zinoviewk.dishesapp.core

import android.content.Context
import androidx.annotation.StringRes
import ru.zinoviewk.dishesapp.presentation.di.core.AppContext
import javax.inject.Inject

interface ResourceProvider {

    fun getString(@StringRes id: Int) : String
}

/**
 * Provides resources
 * @param context is our ApplicationContext
 * */
class ResourceProviderImpl @Inject constructor(
    @AppContext private val context: Context
) : ResourceProvider {

    override fun getString(id: Int): String = context.getString(id)
}