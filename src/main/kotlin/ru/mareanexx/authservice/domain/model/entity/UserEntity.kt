package ru.mareanexx.authservice.domain.model.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table(name = "user")
data class UserEntity(
    @Id
    val idUser: Int? = null, // Автоматическая генерация ID
    val username: String,
    val password: String,
    val email: String,
    val phoneNumber: String,
    val role: String = "USER", // По умолчанию пользователь
    val createdAt: LocalDateTime = LocalDateTime.now()
)
