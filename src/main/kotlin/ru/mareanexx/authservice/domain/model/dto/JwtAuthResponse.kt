package ru.mareanexx.authservice.domain.model.dto

data class JwtAuthResponse(
    val accessToken: String,
    val tokenType: String = "Bearer"
)