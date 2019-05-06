package com.pants.raceuserservice

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import liquibase.integration.spring.SpringLiquibase
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class DbConfig(@Value("\${spring.datasource.url}") val dbUrl: String) {

    @Bean
    fun dataSource(): DataSource {
        val config = HikariConfig().apply {
            jdbcUrl = dbUrl
        }
        return HikariDataSource(config)
    }

    @Bean
    fun liquibase(): SpringLiquibase {
        return SpringLiquibase().apply {
            changeLog = "classpath:db.changelog-master.yaml"
            dataSource = dataSource()
        }
    }

}