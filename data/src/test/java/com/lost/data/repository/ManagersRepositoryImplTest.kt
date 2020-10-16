package com.lost.data.repository

import com.lost.data.datastore.ManagersMemoryDataStore
import com.lost.data.datastore.ManagersRemoteDataStore
import com.lost.data.mapper.ManagersMapper
import com.lost.data.models.ManagersResponse
import com.lost.domain.models.Manager
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@ExperimentalCoroutinesApi
internal class ManagersRepositoryImplTest {
    private val remoteDataStore: ManagersRemoteDataStore = mock()
    private val memoryDataStore: ManagersMemoryDataStore = mock()
    private val managersMapper = ManagersMapper()
    private val managersRepositoryImpl =
        ManagersRepositoryImpl(
            remoteDataStore,
            memoryDataStore,
            managersMapper
        )

    @Test
    fun testGetManagersFromRemote() {
        runBlockingTest {
            whenever(remoteDataStore.get()).thenReturn(provideManagersResponse())
            val returnValue = managersRepositoryImpl.get(null)
            assert(
                returnValue == listOf(
                    provideMappedManager(mockManagerAnne),
                    provideMappedManager(mockManagerBob)
                )
            )
        }
    }

    @Test
    fun testGetManagersResultStored() {
        runBlockingTest {
            val managersResponse = provideManagersResponse()
            whenever(remoteDataStore.get()).thenReturn(managersResponse)
            managersRepositoryImpl.get(null)
            verify(memoryDataStore).set(managersResponse)
        }
    }

    @Test
    fun testGetManagersFromMemory() {
        runBlockingTest {
            whenever(memoryDataStore.get()).thenReturn(provideManagersResponse())
            whenever(remoteDataStore.get()).thenReturn(null)
            val returnValue = managersRepositoryImpl.get(null)
            assert(
                returnValue == listOf(
                    provideMappedManager(mockManagerAnne),
                    provideMappedManager(mockManagerBob)
                )
            )
        }
    }

    @Test
    fun testGetManagersFilter() {
        runBlockingTest {
            whenever(remoteDataStore.get()).thenReturn(provideManagersResponse())
            val returnValue = managersRepositoryImpl.get("A")
            assert(returnValue == listOf(provideMappedManager(mockManagerAnne)))
        }
    }

    private fun provideManagersResponse(): ManagersResponse {
        val dataList = ArrayList<ManagersResponse.Data>()
        val includedList = ArrayList<ManagersResponse.Included>()

        dataList.add(provideData(mockManagerAnne))
        dataList.add(provideData(mockManagerBob))
        includedList.add(provideIncluded(mockManagerAnne))
        includedList.add(provideIncluded(mockManagerBob))
        return ManagersResponse(dataList, includedList)
    }

    private fun provideData(manager: Triple<String, String, String>): ManagersResponse.Data {
        val dataAttributes = ManagersResponse.Data.Attributes(manager.second)
        val accountDataStore =
            ManagersResponse.Data.Relationships.Account.AccountData(manager.first)
        val account = ManagersResponse.Data.Relationships.Account(accountDataStore)
        val relationships = ManagersResponse.Data.Relationships(account)
        return ManagersResponse.Data(dataAttributes, relationships)
    }

    private fun provideIncluded(manager: Triple<String, String, String>): ManagersResponse.Included {
        val includedAttributes = ManagersResponse.Included.Attributes(manager.third)
        return ManagersResponse.Included(manager.first, includedAttributes)
    }

    private fun provideMappedManager(manager: Triple<String, String, String>): Manager {
        return Manager(manager.second, manager.third)
    }

    companion object {
        val mockManagerAnne = Triple("123", "Anne First", "anne@first.com")
        val mockManagerBob = Triple("321", "Bob Second", "bob@second.com")
    }
}