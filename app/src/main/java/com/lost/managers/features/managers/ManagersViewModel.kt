package com.lost.managers.features.managers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lost.domain.models.Manager
import com.lost.domain.usecase.GetManagersUseCase
import com.lost.popeat.features.base.BaseNetworkViewModel
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.launch
import javax.inject.Inject

@ActivityScoped
class ManagersViewModel @Inject constructor(val getManagersUseCase: GetManagersUseCase) :
    BaseNetworkViewModel() {

    private val managersList = MutableLiveData<List<Manager>>()
    fun managersList(): LiveData<List<Manager>> = managersList

    fun getManagers(filter: String? = null) {
        viewModelScope.launch(exceptionHandler) {
            managersList.value = getManagersUseCase(filter)
        }.addToLoadingState()
    }
}