package com.sonrisa.barath.PeldaProject.domain

import java.util.HashSet

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToMany
import javax.persistence.Table

@Entity
@Table(name = "roles")
class Role {
    @Id
    @GeneratedValue
    var id: Long = 0

    lateinit var role: String

    @ManyToMany(mappedBy = "roles")
    var users: Set<User> = HashSet()

    constructor()
    
    constructor(role: String): super(){
        this.role = role
    }
    override fun toString(): String {
        return "Role [id=$id, role=$role]"
    }

}