package ru.mareanexx.authservice.domain.model.dto

data class UserLoginRequest(
    val email: String,
    val password: String
)