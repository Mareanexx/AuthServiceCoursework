package ru.mareanexx.authservice.domain.model.dto

data class UserRegistrationRequest(
    val username: String,
    val password: String,
    val email: String,
    val phoneNumber: String
)