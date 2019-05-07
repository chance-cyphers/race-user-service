package com.pants.raceuserservice

import org.springframework.context.annotation.Profile
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
@Profile("!local")
class AnotherController {

    var counter = 0

    @KafkaListener(topics = ["tuh5qbtu-default"])
    fun listen() {
        counter++
    }

}