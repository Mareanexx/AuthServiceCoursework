package ru.mareanexx.authservice.domain.repository

import org.springframework.data.jdbc.repository.query.Modifying
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import ru.mareanexx.authservice.domain.model.entity.UserEntity

@Repository
interface UserRepository : CrudRepository<UserEntity, Int> {
    @Query("""
        SELECT * FROM "user"
        WHERE email = :email
    """)
    fun findByEmail(@Param("email") email: String): UserEntity?
}