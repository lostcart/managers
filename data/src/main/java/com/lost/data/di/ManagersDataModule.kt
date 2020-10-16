package com.lost.data.di

import com.lost.data.datastore.ManagersDataStore
import com.lost.data.datastore.ManagersMemoryDataStore
import com.lost.data.datastore.ManagersRemoteDataStore
import com.lost.data.repository.ManagersRepositoryImpl
import com.lost.domain.repository.ManagersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
abstract class ManagersDataModule {

    @Binds
    internal abstract fun bindRepository(impl: ManagersRepositoryImpl): ManagersRepository

    @Binds
    internal abstract fun bindRemote(impl: ManagersRemoteDataStore): ManagersDataStore.Remote

    @Binds
    internal abstract fun bindMemory(impl: ManagersMemoryDataStore): ManagersDataStore.Memory
}