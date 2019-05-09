package com.pants.raceuserservice

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.sql.DataSource


@RestController
class WinsController(@Autowired val repository: HelloRepository) {

    var counter = 0

    @RequestMapping("/")
    fun hello(): String {
        return "Race api was visited $counter times, lately"
    }

    @RequestMapping("/hello")
    fun sup(): List<Hello> {
        repository.save(Hello(greeting = "hello and stuff"))
        return repository.findByGreeting("hello and stuff")
    }

}