package com.pants.raceuserservice

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class WinsController {

    var counter = 0

    @KafkaListener(topics = ["tuh5qbtu-default"])
    fun listen() {
        counter++
    }

    @RequestMapping("/")
    fun hello(): String {
        return "Race api was visited $counter times, lately"
    }

    @RequestMapping("/db-test")
    fun count(): String {
        return "sd"
    }



}