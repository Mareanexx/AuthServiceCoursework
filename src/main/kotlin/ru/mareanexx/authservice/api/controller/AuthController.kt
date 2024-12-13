package ru.mareanexx.authservice.api.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import ru.mareanexx.authservice.domain.model.dto.JwtAuthResponse
import ru.mareanexx.authservice.domain.model.dto.UserLoginRequest
import ru.mareanexx.authservice.domain.model.dto.UserRegistrationRequest
import ru.mareanexx.authservice.domain.service.AuthService

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val authService: AuthService
) {
    @PostMapping("/login")
    fun login(@RequestBody loginDto: UserLoginRequest): ResponseEntity<JwtAuthResponse> {
        val response = authService.login(loginDto)

        val jwtAuthResponse = JwtAuthResponse(
            accessToken = response.first,
            idUser = response.second
        )
        println("Send response json = $jwtAuthResponse")

        return ResponseEntity(jwtAuthResponse, HttpStatus.OK)
    }

    @PostMapping("/register")
    fun register(@RequestBody userRequest: UserRegistrationRequest): ResponseEntity<Map<String, String>> {
        return try {
            authService.register(userRequest)
            ResponseEntity(mapOf("message" to "User successfully registered!"), HttpStatus.CREATED)
        } catch (e: IllegalArgumentException) {
            ResponseEntity(mapOf("error" to "User already exists!"), HttpStatus.BAD_REQUEST)
        }
    }
}
