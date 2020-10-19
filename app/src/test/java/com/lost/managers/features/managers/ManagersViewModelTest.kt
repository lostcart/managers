package com.lost.managers.features.managers

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.lost.domain.models.Manager
import com.lost.domain.usecase.GetManagersUseCase
import com.lost.managers.MainCoroutineRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.*
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
internal class ManagersViewModelTest {
    @get:Rule
    val instantExecutor = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val getManagersUseCase: GetManagersUseCase = mock()
    private val managersViewModel = ManagersViewModel(getManagersUseCase)

    @Test
    fun testGetManagers() {
        runBlockingTest {
            val data = listOf(Manager("Test name", "test@email.com"))
            whenever(getManagersUseCase(null)).thenReturn(data)
            managersViewModel.getManagers()
            assert(managersViewModel.managersList().value == data)
        }
    }
}