package com.lost.domain.models

sealed class DomainError {
    object Offline : DomainError()
    object Timeout : DomainError()
    object Unauthorized : DomainError()
    object Forbidden : DomainError()

    sealed class Generic(open val message: String) : DomainError() {
        data class Http(override val message: String, val code: Int) : Generic(message)
        data class Unknown(override val message: String) : Generic(message)
    }
}