package ru.zinoviewk.dishesapp.presentation.di.core

import dagger.Binds
import dagger.Module
import ru.zinoviewk.dishesapp.data.cloud.CloudDataSource
import ru.zinoviewk.dishesapp.data.cloud.CloudDataSourceImpl
import ru.zinoviewk.dishesapp.core.NetworkConnection
import ru.zinoviewk.dishesapp.core.NetworkConnectionImpl
import javax.inject.Singleton

@Module
abstract class CloudModule {

    @Singleton
    @Binds
    abstract fun bindCloudDataSource(cloudDataSource: CloudDataSourceImpl) : CloudDataSource

    @Singleton
    @Binds
    abstract fun bindNetworkConnection(networkConnection: NetworkConnectionImpl): NetworkConnection
}