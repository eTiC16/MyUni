package com.example.myuni.ui.dashboard

import androidx.lifecycle.*
import com.example.myuni.data.remote.EntityDto
import com.example.myuni.data.repository.UniRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: UniRepository
) : ViewModel() {

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    private val _entities = MutableLiveData<List<EntityDto>>()
    val entities: LiveData<List<EntityDto>> = _entities

    fun loadDashboard(keypass: String) {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null
            val result = repository.getDashboard(keypass)
            result
                .onSuccess { response -> _entities.value = response.entities }
                .onFailure { e -> _error.value = e.message ?: "Failed to load dashboard" }
            _loading.value = false
        }
    }
}
