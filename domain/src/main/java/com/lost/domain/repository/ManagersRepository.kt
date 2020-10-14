package com.lost.domain.repository

import com.lost.domain.models.Manager

interface ManagersRepository {
    suspend fun get(filter: String?): List<Manager>?
}