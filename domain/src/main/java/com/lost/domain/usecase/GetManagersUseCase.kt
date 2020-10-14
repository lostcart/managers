package com.lost.domain.usecase

import com.lost.domain.repository.ManagersRepository
import javax.inject.Inject

class GetManagersUseCase @Inject constructor(private val repository: ManagersRepository){
    suspend operator fun invoke(filter: String?) = repository.get(filter)
}