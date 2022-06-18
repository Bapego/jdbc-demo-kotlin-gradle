package com.sonrisa.barath.PeldaProject.controller

import com.sonrisa.barath.PeldaProject.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ApiController {
    var productService: ProductService? = null
        @Autowired
        set(value) {
            field = value
        }


//
//    @RequestMapping("/testDevTools")
//    public fun firstProduct(): String? {
//       return "almasdasdaaasd"
//    }
//
//    @RequestMapping("/products/{name}")
//    public fun searchForArtist(@PathVariable(value="name") name: String): String {
//        return productService?.getSpecificProduct(name).toString()
//    }
//
//    @RequestMapping("/artist/{name}")
//    public fun searchProductsByArtistName(@PathVariable(value="name") name: String): List<Product>? {
//        return productService?.getProductsByArtistName(name)
//    }
}