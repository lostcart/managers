package com.lost.data.mapper

import com.lost.domain.models.DomainError
import retrofit2.HttpException
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class DomainErrorMapper @Inject constructor() : Mapper<Throwable, DomainError>() {

    override fun mapFrom(from: Throwable): DomainError {
        return when (from) {
            is UnknownHostException -> {
                DomainError.Offline
            }
            is SocketTimeoutException -> {
                DomainError.Timeout
            }
            is HttpException -> {
                when (from.code()) {
                    HttpURLConnection.HTTP_UNAUTHORIZED -> DomainError.Unauthorized
                    HttpURLConnection.HTTP_FORBIDDEN -> DomainError.Forbidden
                    else -> DomainError.Generic.Http(message = from.message(), code = from.code())
                }
            }
            else -> {
                DomainError.Generic.Unknown(message = from.message ?: "")
            }
        }
    }
}