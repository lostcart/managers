package com.lost.domain.usecase

import com.lost.domain.repository.ManagersRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Test

@ExperimentalCoroutinesApi
internal class GetManagersUseCaseTest {
    private val repository: ManagersRepository = mock()
    private val useCase = GetManagersUseCase(repository)

    @After
    fun tearDown() {
        verifyNoMoreInteractions(repository)
    }

    @Test
    fun testShouldCallGet() {
        runBlockingTest {
            useCase.invoke(null)
            verify(repository).get(null)
        }
    }
}