package com.sonrisa.barath.PeldaProject.repository

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class ArtistRepository {
    private var jdbc: JdbcTemplate? = null
        @Autowired
        set(value) {
            field = value
        }
}