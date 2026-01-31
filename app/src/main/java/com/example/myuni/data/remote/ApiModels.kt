package com.example.myuni.data.remote

data class LoginRequest(
    val username: String,
    val password: String
)

data class LoginResponse(
    val keypass: String
)

data class EntityDto(
    val property1: String,
    val property2: String,
    val description: String
)

data class DashboardResponse(
    val entities: List<EntityDto>,
    val entityTotal: Int
)
