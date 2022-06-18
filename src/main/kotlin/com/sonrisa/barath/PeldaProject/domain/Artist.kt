// Kommentben JPA-hez tartoznak az annotációk
package com.sonrisa.barath.PeldaProject.domain

import com.fasterxml.jackson.annotation.JsonBackReference


class Artist(
        var id: Long,
        var name: String,
        var age: Int,

        @JsonBackReference
        var products: List<Product>
) {
    constructor() : this(0, "",
            0, emptyList()
    )

    constructor(id: Long, name: String, age: Int) : this() {
        this.id = id
        this.name = name
        this.age = age

    }

    override fun toString(): String {
        return "[" + this.id + ", " + this.name + ", " + this.age + ", " + this.products + "]"
    }
}