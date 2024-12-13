package ru.mareanexx.authservice.domain.service
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import ru.mareanexx.authservice.api.util.JwtTokenProvider
import ru.mareanexx.authservice.domain.model.dto.UserLoginRequest
import ru.mareanexx.authservice.domain.model.dto.UserRegistrationRequest
import ru.mareanexx.authservice.domain.model.entity.UserEntity
import ru.mareanexx.authservice.domain.repository.UserRepository
import kotlin.math.log

@Service
class AuthService(
    private val authenticationManager: AuthenticationManager,
    private val jwtTokenProvider: JwtTokenProvider,
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {
    fun login(loginDto: UserLoginRequest): Pair<String, Int> {
        println("Authenticating user: ${loginDto.email}")
        val authentication: Authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                loginDto.email,
                loginDto.password
            )
        )
        println("Authentication successful for: ${loginDto.email}")

        SecurityContextHolder.getContext().authentication = authentication

        val user = userRepository.findByEmail(loginDto.email)

        return Pair(jwtTokenProvider.generateToken(authentication), user?.idUser!!)
    }


    fun register(userRequest: UserRegistrationRequest) {
        println("Register user: ${userRequest.email}")
        // Check if email already exists
        if (userRepository.findByEmail(userRequest.email) != null) {
            throw IllegalArgumentException("User with email ${userRequest.email} already exists.")
        }
        println("User is UNIQUE: ${userRequest.email}")

        // Hash password
        val encodedPassword = passwordEncoder.encode(userRequest.password)

        // Create a new UserEntity
        val newUser = UserEntity(
            username = userRequest.username,
            email = userRequest.email,
            password = encodedPassword,
            phoneNumber = userRequest.phoneNumber
        )
        try {
            userRepository.save(newUser)
        }
        catch (e: IllegalArgumentException) {
            println(e.message)
            println("its here, so it cant save the user")
        }
        // Save to repository
        println("after save: Registration successful for: ${userRequest.email}")
    }
}
