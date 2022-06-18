package com.sonrisa.barath.PeldaProject

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
class PeldaProjectApplication

fun main(args: Array<String>) {
    runApplication<PeldaProjectApplication>(*args)
}
