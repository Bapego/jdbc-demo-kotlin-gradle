package com.sonrisa.barath.PeldaProject.service

import com.sonrisa.barath.PeldaProject.domain.Role
import com.sonrisa.barath.PeldaProject.domain.User
import com.sonrisa.barath.PeldaProject.repository.RoleRepository
import com.sonrisa.barath.PeldaProject.repository.UserRepository
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.Random
import org.springframework.security.core.context.SecurityContextHolder




@Service
@Configuration
class UserServiceImpl: UserService, UserDetailsService {

    private val log = LogFactory.getLog(this.javaClass)

    var emailService: EmailService ?= null
        @Autowired
        set(value) {
            field = value
        }

    val USER_ROLE: String = "USER"
    var userRepository: UserRepository? = null
    @Autowired
    set(value) {
        field = value
    }
    var roleRepository: RoleRepository? = null
        @Autowired
        set(value) {
            field = value
        }

    override fun loadUserByUsername(username: String?): UserDetails {
        var user: User? = findByEmail(username!!)
        if(user == null){
            throw UsernameNotFoundException(username) as Throwable
        }
        return UserDetailsImpl(user)
    }
    override fun findByEmail(email: String): User? {
        return userRepository?.findByEmail(email);
    }

    override fun registerUser(userToRegister: User): String? {
        var randomCode = generateKey()
        var userCheck: User? = userRepository?.findByEmail(userToRegister?.email)

        if(userCheck != null)
            return "alreadyExists"

        var userRole: Role? = roleRepository?.findByRole(USER_ROLE)
        if(userRole != null) {
            (userToRegister.roles as HashSet<Role>).add(userRole)
        } else {
            userToRegister.addRoles(USER_ROLE)
        }
        userToRegister.enabled = false
        userToRegister.activation = randomCode

        userRepository?.save(userToRegister)

        emailService?.sendMessage(userToRegister.email, randomCode)
        return "ok"
    }

    fun generateKey(): String {
        val key = ""
        val random = Random()
        val word = CharArray(16)
        for (j in word.indices) {
            word[j] = ('a'.toInt() + random.nextInt(26)).toChar()
        }
        val toReturn = String(word)
        log.debug("random code: $toReturn")
        return String(word)
    }

    override fun userActivation(code: String?): String? {
        val user = userRepository?.findByActivation(code)
        if(user == null)  return "noresult"
        user.enabled = true
        user.activation = ""
        userRepository?.save(user)


        return "ok"
    }

    override fun getNameByEmail(email: String): String? {
       var name = userRepository?.findByEmail(email)?.fullName
        return name
    }

    fun getNameByEmailAuth(): String? {
        val auth = SecurityContextHolder.getContext().authentication
        if(auth.name == "anonymousUser") return "Anonymous"
        return getNameByEmail(auth.name)
    }

}