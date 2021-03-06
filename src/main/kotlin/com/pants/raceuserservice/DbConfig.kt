package com.pants.raceuserservice

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class DbConfig(@Value("\${spring.datasource.url}") val dbUrl: String) {

    @Bean
    fun dataSource(): DataSource {
        println("using this url: $dbUrl")
        val config = HikariConfig().apply {
            jdbcUrl = dbUrl
        }
        return HikariDataSource(config)
    }

}