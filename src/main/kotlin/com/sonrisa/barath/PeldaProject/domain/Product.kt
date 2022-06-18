package com.sonrisa.barath.PeldaProject.domain

import java.util.*

class Product(
        var id: Long = 0,
        var name: String = "",
        var description: String = "", //descreption
        var discount: Int = -1,
        var eod: Date = Date(), //endOfDiscount
        var price: Int = -1,
        var artist: Artist? = null
) {


    override fun toString(): String {
        return "[" + this.id + ", " + this.name + ", " + this.description + ", " + this.discount + ", " + this.artist + "]\n"
    }
}