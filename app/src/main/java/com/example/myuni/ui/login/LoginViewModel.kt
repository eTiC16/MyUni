package com.example.myuni.ui.login

import androidx.lifecycle.*
import com.example.myuni.data.repository.UniRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: UniRepository
) : ViewModel() {

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    private val _loginSuccess = MutableLiveData<String?>()
    val loginSuccess: LiveData<String?> = _loginSuccess

    fun login(campus: String, username: String, password: String) {
        if (username.isBlank() || password.isBlank()) {
            _error.value = "Please enter username and password"
            return
        }

        viewModelScope.launch {
            _loading.value = true
            _error.value = null
            val result = repository.login(campus, username, password)
            result
                .onSuccess { keypass -> _loginSuccess.value = keypass }
                .onFailure { e -> _error.value = e.message ?: "Login failed" }
            _loading.value = false
        }
    }
}
