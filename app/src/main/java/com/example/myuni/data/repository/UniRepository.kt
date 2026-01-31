package com.example.myuni.data.repository

import com.example.myuni.data.remote.ApiService
import com.example.myuni.data.remote.DashboardResponse
import javax.inject.Inject

class UniRepository @Inject constructor(
    private val api: ApiService
) {

    suspend fun login(campus: String, username: String, password: String): Result<String> =
        runCatching {
            val res = api.login(campus, com.example.myuni.data.remote.LoginRequest(username, password))
            if (res.isSuccessful) {
                res.body()?.keypass ?: throw IllegalStateException("Empty keypass")
            } else {
                throw IllegalStateException("Login failed: ${res.code()}")
            }
        }

    suspend fun getDashboard(keypass: String): Result<DashboardResponse> =
        runCatching {
            val res = api.getDashboard(keypass)
            if (res.isSuccessful) {
                res.body() ?: throw IllegalStateException("Empty dashboard")
            } else {
                throw IllegalStateException("Dashboard failed: ${res.code()}")
            }
        }
}
