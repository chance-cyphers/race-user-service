package com.pants.raceuserservice

import org.springframework.data.repository.CrudRepository

interface HelloRepository: CrudRepository<Hello, Long> {

    fun findByGreeting(greeting: String): List<Hello>

}