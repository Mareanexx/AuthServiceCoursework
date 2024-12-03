package ru.mareanexx.authservice.domain.service
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import ru.mareanexx.authservice.domain.repository.UserRepository

@Service
class CustomUserDetailsService(
    private val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(userEmail: String): UserDetails {
        val user = userRepository.findByEmail(userEmail)
            ?: throw UsernameNotFoundException("User not exists by Username or Email")

        println("Loaded user: $user, ${user.email}, ${user.username}")
        val authorities: GrantedAuthority = SimpleGrantedAuthority("ROLE_${user.role}")
        println("User ${user.email} has authority '${authorities.authority}'")

        return org.springframework.security.core.userdetails.User(
            userEmail,
            user.password,
            setOf(authorities)
        )
    }
}
