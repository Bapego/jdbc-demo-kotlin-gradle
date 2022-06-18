package com.sonrisa.barath.PeldaProject.repository

import com.sonrisa.barath.PeldaProject.domain.Role
import org.springframework.data.repository.CrudRepository

interface RoleRepository: CrudRepository<Role, Long> {

    fun findByRole(role: String): Role?

}