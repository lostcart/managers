package com.lost.managers.features.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.lost.managers.MainCoroutineRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.*
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import java.lang.IllegalArgumentException

@ExperimentalCoroutinesApi
internal class BaseViewModelImplTest {
    @get:Rule
    val instantExecutor = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val testUseCase: TestUseCase = mock()
    private val baseViewModelImpl = BaseViewModelImpl(testUseCase)

    @Test
    fun testValue() {
        runBlockingTest {
            launch(TestCoroutineDispatcher()) {
                whenever(testUseCase.invoke()).thenReturn("Hello")
                baseViewModelImpl.init()
                assert(baseViewModelImpl.testValue().value == "Hello")
            }
        }
    }

    @Test
    fun testLoading() {
        runBlockingTest {
            launch(TestCoroutineDispatcher()) {
                baseViewModelImpl.init()
                assert(baseViewModelImpl.isLoading().value == false)
            }
        }
    }

    @Test
    fun testError() {
        runBlockingTest {
            launch(TestCoroutineDispatcher()) {
                whenever(testUseCase()).thenThrow(IllegalArgumentException())
                baseViewModelImpl.init()
                assert(baseViewModelImpl.error().value != null)
            }
        }
    }
}