package com.sonrisa.barath.PeldaProject.service

import com.sonrisa.barath.PeldaProject.domain.User
import org.springframework.context.annotation.Configuration
import org.springframework.data.repository.NoRepositoryBean
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

// Valamiért nem működik ezzel
interface UserService {
    fun registerUser(user: User): String?

    fun findByEmail(email: String): User?

    fun userActivation(code: String?): String?

    fun getNameByEmail(email: String): String?
}