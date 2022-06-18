package com.sonrisa.barath.PeldaProject.repository

import com.sonrisa.barath.PeldaProject.domain.Artist
import com.sonrisa.barath.PeldaProject.domain.Product
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository


@Repository
class ProductRepository {
    private var jdbc: JdbcTemplate? = null
        @Autowired
        set(value) {
            field = value
        }

    private fun getArtistById(id: Long): Artist? {
        var sql: String = "select * from artists where id = ?"
        var query = jdbc?.queryForObject(
                sql,
                arrayOf(id), // Ebbe lehetne írni a többi paramétert is
                RowMapper { rs, rowNum ->
                    Artist(
                            id = rs.getLong("id"),
                            name = rs.getString("name"),
                            age = rs.getInt("age"))
                }
        )
        return query
    }

    fun findAll(): MutableList<Product>? {
        var sql: String = "select * from products order by id desc"
        var query = jdbc?.query(sql, RowMapper { rs, i ->
            Product(
                    id = rs.getLong("id"),
                    name = rs.getString("name"),
                    description = rs.getString("description"),
                    discount = rs.getInt("discount"),
                    eod = rs.getDate("eod"),
                    price = rs.getInt("price"),
                    artist = getArtistById(rs.getLong("artist_id"))
            )
        })
        return query
    }

    fun findByName(name: String): Product? {
        val sql = "select * from products where name = ? order by id"
        return jdbc?.queryForObject(
                sql,
                arrayOf(name), // Ebbe lehetne írni a többi paramétert is
                RowMapper { rs, i ->
                    Product(
                            id = rs.getLong("id"),
                            name = rs.getString("name"),
                            description = rs.getString("description"),
                            discount = rs.getInt("discount"),
                            eod = rs.getDate("eod"),
                            price = rs.getInt("price"),
                            artist = getArtistById(rs.getLong("artist_id")))
                }
        )
    }
}