package com.lost.data.repository

import android.util.Log
import com.lost.data.datastore.ManagersDataStore
import com.lost.data.di.DispatcherProvider
import com.lost.data.mapper.ManagersMapper
import com.lost.domain.models.Manager
import com.lost.domain.repository.ManagersRepository
import kotlinx.coroutines.*
import javax.inject.Inject

internal class ManagersRepositoryImpl @Inject constructor(
    private val remote: ManagersDataStore.Remote,
    private val memory: ManagersDataStore.Memory,
    private val managersMapper: ManagersMapper,
    private val dispatchers: DispatcherProvider
) : ManagersRepository {

    override suspend fun get(filter: String?): List<Manager>? {
        val source = memory.get() ?: remote.get().also(memory::set)
        var restaurants = managersMapper.mapFrom(source)
        Log.d("LUKELUKE", restaurants.toString())
        return restaurants
    }

    companion object {
    }
}