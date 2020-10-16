package com.lost.data.repository

import com.lost.data.datastore.ManagersDataStore
import com.lost.data.mapper.ManagersMapper
import com.lost.domain.models.Manager
import com.lost.domain.repository.ManagersRepository
import javax.inject.Inject

internal class ManagersRepositoryImpl @Inject constructor(
    private val remote: ManagersDataStore.Remote,
    private val memory: ManagersDataStore.Memory,
    private val managersMapper: ManagersMapper
) : ManagersRepository {

    override suspend fun get(filter: String?): List<Manager>? {
        val source = memory.get() ?: remote.get().also(memory::set)
        var managers = managersMapper.mapFrom(source)
        filter?.let {
            managers = managers.filter { manager ->
                val toCheck = "$manager.name $manager.email"
                toCheck.contains(it, true)
            }
        }
        return managers
    }
}