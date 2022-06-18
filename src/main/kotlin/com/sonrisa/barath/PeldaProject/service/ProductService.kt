package com.sonrisa.barath.PeldaProject.service

import com.sonrisa.barath.PeldaProject.domain.Product
import com.sonrisa.barath.PeldaProject.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProductService {
    private var productRepo: ProductRepository? = null
        @Autowired
        set(value) {
            field = value
        }

    fun getProducts(): MutableList<Product> {
        return productRepo?.findAll() as MutableList<Product>
    }

    //    fun getFirstProduct(): Product? {
//        return productRepo?.findFirstByOrderByIdDesc()
//    }
//
    fun getSpecificProduct(name: String): Product? {
        try {
            return productRepo?.findByName(name)
        } catch (e: Exception) {
            throw Exception("Haver! Nem jó nevet adtál meg!")
        }
    }
//
//    fun getProductsByArtistName(name: String): List<Product>? {
//        return productRepo?.findAllByArtistNameIgnoreCaseOrderByIdDesc(name)
//    }

//    @PostConstruct
//    fun init(): Unit{
//        var artist = Artist(name = "Belső Benő",age = 22)
//        var product = Product(price = 20000, name = "Birs Alma", desc = "Ezt a gyümölcsöt kevesen szeretik", discount = 10, eod = Date(),artist=artist)
//        //artistRepo?.save(artist)
//        productRepo?.save(product)
//    }
}