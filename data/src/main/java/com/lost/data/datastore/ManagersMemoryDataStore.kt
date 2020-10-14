package com.lost.data.datastore

import com.lost.data.api.ManagersApiService
import com.lost.data.models.ManagersResponse
import javax.inject.Inject

internal class ManagersMemoryDataStore @Inject constructor(
) : ManagersDataStore.Memory {

    private var managersResponse: ManagersResponse? = null

    override fun set(managersResponse: ManagersResponse) {
        this.managersResponse = managersResponse
    }

    override fun get(): ManagersResponse? {
        return managersResponse
    }
}