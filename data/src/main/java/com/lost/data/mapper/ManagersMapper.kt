package com.lost.data.mapper

import com.lost.data.models.ManagersResponse
import com.lost.domain.models.Manager
import javax.inject.Inject

internal class ManagersMapper @Inject constructor() : Mapper<ManagersResponse?, List<Manager>>() {

    override fun mapFrom(managersResponse: ManagersResponse?): List<Manager> {
        val managers = HashMap<String, Manager>()
        managersResponse?.let {
            it.data.forEach { data ->
                managers[data.relationships.account.data.id] = Manager(
                    data.attributes.firstName,
                    data.attributes.lastName
                )
            }
            it.included.forEach { included ->
                val manager = managers[included.id]
                if (manager != null) {
                    manager.email = included.attributes.email
                }
            }
        }
        return managers.values.toList()
    }
}