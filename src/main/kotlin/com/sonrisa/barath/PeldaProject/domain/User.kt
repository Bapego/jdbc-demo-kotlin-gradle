package com.sonrisa.barath.PeldaProject.domain

import javax.persistence.*


@Entity
@Table(name="users")
class User {
    @Id
    @GeneratedValue
    var id: Long = 0
    @Column(unique=true, nullable=false)
    var email: String? = null
    @Column(nullable=false)
    var password: String? = null

    var fullName: String? = null
    var activation: String? = null
    var enabled: Boolean = true

    @ManyToMany(cascade = arrayOf(CascadeType.ALL), fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = [JoinColumn(name = "user_id")],
            inverseJoinColumns =  [JoinColumn(name = "role_id")]
    )
    var roles: Set<Role>? = HashSet<Role>()

    constructor()

    constructor(username: String, password: String, fullName: String) : super() {
        this.email = username
        this.password = password
        this.fullName = fullName
    }

    fun addRoles(roleName: String){
        if(roles == null || roles!!.isEmpty()) roles = HashSet<Role>()
        println("IdeLépj:" + roleName)
        (roles as HashSet<Role>).add(Role(roleName))
    }
}