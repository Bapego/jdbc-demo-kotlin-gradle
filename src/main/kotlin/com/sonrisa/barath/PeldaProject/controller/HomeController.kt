package com.sonrisa.barath.PeldaProject.controller

import com.sonrisa.barath.PeldaProject.domain.User
import com.sonrisa.barath.PeldaProject.repository.UserRepository
import com.sonrisa.barath.PeldaProject.service.EmailService
import com.sonrisa.barath.PeldaProject.service.ProductService
import com.sonrisa.barath.PeldaProject.service.UserService
import com.sonrisa.barath.PeldaProject.service.UserServiceImpl
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.servlet.http.HttpServletRequest


@Controller
class HomeController {
    private val log = LogFactory.getLog(this.javaClass)

    var productService: ProductService? = null
        @Autowired
        set(value) {
            field = value
        }

    // Itt hibát dobott a setteres injektálásra
    var userService: UserService? = null
        @Autowired
        set(value) {
            field = value
        }

    @RequestMapping("/", "/index")
    public fun index(model: Model, locale: Locale): String {
        model.addAttribute("pageTitleJava", "Virtua - Virtuális izé")
//        println("A jelenlegi nyelv: ${locale.displayLanguage}\nAz ország: ${locale.displayCountry}")
        return "index"
    }

    // Csak felhasználó érheti el
//    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping("/termekek")
    public fun products(model: Model): String {
        model.addAttribute("pageTitleJava", "Virtua - Virtuális izé")
        model.addAttribute("products", productService?.getProducts())
        return "products"
    }

    //    @RequestMapping("/firstProduct")
//    public fun firstProduct(model: Model): String {
//        println(productService?.getFirstProduct()?.toString())
//        model.addAttribute("product", productService?.getFirstProduct())
//        return "services"
//    }
//    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping("/termekek/{name}")
    public fun searchForArtist(@PathVariable(value = "name") name: String = "Alma", model: Model): String {
        model.addAttribute("pageTitleJava", "Virtua - Virtuális izé")
        model.addAttribute("product", productService?.getSpecificProduct(name))
        return "services"
    }

    @RequestMapping("/story")
    public fun story(): String {
        return "story"
    }

    @RequestMapping("/rolunk")
    public fun aboutUs(): String {
        return "about-us"
    }

    @RequestMapping("/galeria")
    public fun gallery(): String {
        return "gallery"
    }

    @RequestMapping("/kapcsolat")
    public fun contact(): String {
        return "contact"
    }

    @RequestMapping("/premium")
    public fun premium(): String {
        return "premium"
    }

    //    // Exception gyakorlás
//    @Secured("ADMIN")
    @Throws(Exception::class)
    @RequestMapping("/user/{id}")
    fun searchForUser(@PathVariable(value = "id") id: String): String {
        if (id == "null")
            throw Exception("Nincs ilyen user")
        return "user"
    }

    @RequestMapping("/regisztracio")
    fun registration(model: Model): String {
        log.info("Szia log én vagyok az új user!")
        model.addAttribute("user", User())
        return "auth/registration"
    }

    //	@RequestMapping(value = "/reg", method = RequestMethod.POST)
    @PostMapping("/reg")
    fun greetingSubmit(@ModelAttribute user: User): String {
//        log.info("Szia log én vagyok az új user!")
//        log.debug(user.fullName)
//        log.debug(user.email)
//        log.debug(user.password)
        userService?.registerUser(user)
        return "auth/login"
    }

    @RequestMapping(path = arrayOf("/activation/{code}"), method = arrayOf(RequestMethod.GET))
    fun activation (@PathVariable("code") code: String, response: HttpServletRequest): String{
        var result: String? = userService?.userActivation(code)
        return "auth/login"
    }
    //Ez a funkció már a service-ben van
//    private fun getProducts(): MutableList<Product> {
//        var products: MutableList<Product> = productRepo?.findAll() as MutableList<Product>
//        return products
////        // Manuális adat betétel, nincs DB használat
////        var products: MutableList<Product> = mutableListOf<Product>()
////
////        var prodMin = Product(1,"Mini Szolgáltatás", "Ez egy olyan mini szolgáltatás, ami baromira nem éri meg neked, vedd meg a drágábbat!",0, Date(),200_000)
////        var prodMed = Product(2,"Medium Szolgáltatás", "Majdnem, de ez még nem a legdrágább! Huss tovább!",20, Date(), 600_000)
////        var prodMax = Product(3,"Max Szolgáltatás", "Ez egy olyan max szolgáltatás, ami baromira drága de nagyon megéri neked, vedd meg!",50, Date(), 10_000_000)
////
////        products.add(prodMin)
////        products.add(prodMed)
////        products.add(prodMax)
////
////
////        return products
//    }
}