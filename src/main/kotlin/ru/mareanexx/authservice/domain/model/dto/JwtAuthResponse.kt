package ru.mareanexx.authservice.domain.model.dto

data class JwtAuthResponse(
    val idUser: Int,
    val accessToken: String,
    val tokenType: String = "Bearer"
)