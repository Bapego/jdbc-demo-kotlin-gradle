package com.sonrisa.barath.PeldaProject.config

import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
// Nézetekkel kapcsolatos dolgokat tudjuk állítani

class webConfig : WebMvcConfigurer {
    override fun addViewControllers(registry: ViewControllerRegistry) {
        super.addViewControllers(registry) // A Registry tárrolja a kapcsolataokat a nézeteket az end-pointok között
        registry.addViewController("/login").setViewName("auth/login")
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE)
    }
}