package com.pants.raceuserservice

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class WinsController {

    var counter = 0

//    init {
//        thread(start = true) {
//            println("starting with the consumption")
//            startConsuming()
//        }
//    }

    @KafkaListener(topics = ["tuh5qbtu-default"])
    fun listen() {
        counter++
    }

    @RequestMapping("/")
    fun hello(): String {
        return "Race api was visited $counter times, lately"
    }

//    fun startConsuming() {
//        val brokers = System.getenv("CLOUDKARAFKA_BROKERS")
//        val username = System.getenv("CLOUDKARAFKA_USERNAME")
//        val password = System.getenv("CLOUDKARAFKA_PASSWORD")
//        val topic = "$username-default"
//
//        val consumer = createConsumer(brokers, username, password)
//        consumer.subscribe(Arrays.asList(topic))
//
//        while (true) {
//            val records = consumer.poll(Duration.ofMillis(1000))
//            records.forEach {
//                println("got a record: " + it.value())
//                counter++
//            }
//        }
//    }

}
//
//private fun createConsumer(brokers: String, username: String, password: String): KafkaConsumer<String, String> {
//    val jaasTemplate = "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"%s\" password=\"%s\";"
//    val jaasCfg = String.format(jaasTemplate, username, password)
//
//    val props = Properties()
//
//    props["bootstrap.servers"] = brokers
//    props["key.deserializer"] = "org.apache.kafka.common.serialization.StringDeserializer"
//    props["value.deserializer"] = "org.apache.kafka.common.serialization.StringDeserializer"
//    props["group.id"] = "$username-consumer"
//    props["security.protocol"] = "SASL_SSL"
//    props["sasl.mechanism"] = "SCRAM-SHA-256"
//    props["sasl.jaas.config"] = jaasCfg
//
//    return KafkaConsumer(props)
//}
