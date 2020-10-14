package com.lost.data.api

import com.lost.data.models.ManagersResponse
import retrofit2.http.GET
import retrofit2.http.Headers

internal interface ManagersApiService {

    @Headers("Content-Type: application/json")
    @GET("41238222ac31fe36348544ee1d4a9a5e/raw/5dc996407f6c9a6630bfcec56eee22d4bc54b518/employees.json")
    suspend fun get(): ManagersResponse
}