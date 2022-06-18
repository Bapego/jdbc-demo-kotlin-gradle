package com.sonrisa.barath.PeldaProject.repository
import com.sonrisa.barath.PeldaProject.domain.User
import org.springframework.data.repository.CrudRepository


interface UserRepository: CrudRepository<User, Long>{
    fun findByEmail(email: String?):User?

    fun findByActivation(code: String?): User?
}
