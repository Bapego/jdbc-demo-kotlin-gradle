package com.sonrisa.barath.PeldaProject.service

import com.sonrisa.barath.PeldaProject.domain.Role
import com.sonrisa.barath.PeldaProject.domain.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.HashSet
import org.springframework.security.crypto.factory.PasswordEncoderFactories

class UserDetailsImpl: UserDetails {

    var user: User

    constructor(user: User){
        this.user = user
    }


    override fun getAuthorities(): Collection<GrantedAuthority> {
        val authorities = HashSet<GrantedAuthority>()
        val roles = user?.roles
        for (role in roles!!) {
            authorities.add(SimpleGrantedAuthority(role.role))
        }
        return authorities
    }

    override fun isEnabled(): Boolean {
        return true
    }

    override fun getUsername(): String? {
        return user?.email
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun getPassword(): String? {
        var encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder()
        return encoder.encode(user?.password)
        //return user?.password
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return user.enabled
    }
}