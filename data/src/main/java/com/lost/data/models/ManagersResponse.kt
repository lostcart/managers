package com.lost.data.models

internal data class ManagersResponse(
    val data: List<Data>,
    val included: List<Included>
) {
    internal data class Data(
        val type: String,
        val id: String,
        val attributes: Attributes,
        val relationships: Relationships
    ) {
        internal data class Attributes(
            val name: String
        )

        internal data class Relationships(
            val account: Account
        ) {
            internal data class Account(
                val data: AccountData
            ) {
                internal data class AccountData(
                    val type: String,
                    val id: String
                )
            }
        }


    }

    internal data class Included(
        val id: String,
        val attributes: Attributes
    ) {
        internal data class Attributes(
            val email: String?
        )
    }
}