package com.lost.data.datastore

import com.lost.data.models.ManagersResponse

internal interface ManagersDataStore {
    interface Memory {
        fun set(managersResponse: ManagersResponse)
        fun get(): ManagersResponse?
    }

    interface Remote {
        suspend fun get(): ManagersResponse
    }
}