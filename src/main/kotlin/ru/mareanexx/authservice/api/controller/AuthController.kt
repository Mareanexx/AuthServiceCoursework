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
    // Build Login REST API
    @PostMapping("/login")
    fun login(@RequestBody loginDto: UserLoginRequest): ResponseEntity<JwtAuthResponse> {
        val token = authService.login(loginDto)

        val jwtAuthResponse = JwtAuthResponse(
            accessToken = token
        )

        return ResponseEntity(jwtAuthResponse, HttpStatus.OK)
    }

    // Build Registration REST API
    @PostMapping("/register")
    fun register(@RequestBody userRequest: UserRegistrationRequest): ResponseEntity<String> {
        return try {
            authService.register(userRequest)
            ResponseEntity("User successfully registered!", HttpStatus.CREATED)
        } catch (e: IllegalArgumentException) {
            ResponseEntity("User already exists!", HttpStatus.BAD_REQUEST)
        }
    }

    // для тестирования
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    fun getSomeInfo(): ResponseEntity<String> {
        return ResponseEntity("User HELLO!!!", HttpStatus.OK)
    }


    // для тестирования
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    fun getSomeInfoAdmin(): ResponseEntity<String> {
        return ResponseEntity("Admin HELLO!!!", HttpStatus.OK)
    }
}
