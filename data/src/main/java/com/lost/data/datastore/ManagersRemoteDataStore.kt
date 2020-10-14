package com.lost.data.datastore

import com.lost.data.api.ManagersApiService
import com.lost.data.models.ManagersResponse
import javax.inject.Inject

internal class ManagersRemoteDataStore @Inject constructor(
    private val managersApiService: ManagersApiService
) : ManagersDataStore.Remote {

    override suspend fun get(): ManagersResponse {
        return managersApiService.get()
    }
}