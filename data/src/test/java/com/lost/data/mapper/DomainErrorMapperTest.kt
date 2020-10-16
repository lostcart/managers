package com.lost.data.mapper

import com.lost.domain.models.DomainError
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.UnknownHostException

internal class DomainErrorMapperTest {
    private val domainErrorMapper = DomainErrorMapper()

    @Test
    fun testOfflineError() {
        val output = domainErrorMapper.mapFrom(UnknownHostException())
        Assert.assertEquals(output, DomainError.Offline)
    }

    @Test
    fun testTimeOutError() {
        val output = domainErrorMapper.mapFrom(SocketTimeoutException())
        Assert.assertEquals(output, DomainError.Timeout)
    }

    @Test
    fun testUnauthorizedError() {
        val response: Response<String> = mock()
        whenever(response.code()).thenReturn(HttpURLConnection.HTTP_UNAUTHORIZED)
        val output = domainErrorMapper.mapFrom(HttpException(response))
        Assert.assertEquals(output, DomainError.Unauthorized)
    }

    @Test
    fun testForbiddenError() {
        val response: Response<String> = mock()
        whenever(response.code()).thenReturn(HttpURLConnection.HTTP_FORBIDDEN)
        val output = domainErrorMapper.mapFrom(HttpException(response))
        Assert.assertEquals(output, DomainError.Forbidden)
    }


    @Test
    fun testGenericHttpError() {
        val response: Response<String> = mock()
        whenever(response.code()).thenReturn(HttpURLConnection.HTTP_BAD_REQUEST)
        whenever(response.message()).thenReturn("some message")
        val output = domainErrorMapper.mapFrom(HttpException(response))
        Assert.assertEquals(
            output,
            DomainError.Generic.Http("some message", HttpURLConnection.HTTP_BAD_REQUEST)
        )
    }

    @Test
    fun testGenericUnknownError() {
        val output = domainErrorMapper.mapFrom(IllegalArgumentException())
        Assert.assertEquals(
            output,
            DomainError.Generic.Unknown("")
        )
    }
}