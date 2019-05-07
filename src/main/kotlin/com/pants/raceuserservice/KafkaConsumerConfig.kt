package com.pants.raceuserservice

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory


@EnableKafka
@Configuration
@Profile("!local")
class KafkaConsumerConfig {

    @Bean
    fun consumerFactory(): ConsumerFactory<String, String> {
        val brokers = System.getenv("CLOUDKARAFKA_BROKERS")
        val username = System.getenv("CLOUDKARAFKA_USERNAME")
        val password = System.getenv("CLOUDKARAFKA_PASSWORD")
        val jaasTemplate = "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"%s\" password=\"%s\";"
        val jaasCfg = String.format(jaasTemplate, username, password)

        val props = hashMapOf<String, Any>(
                "bootstrap.servers" to brokers,
                "key.deserializer" to "org.apache.kafka.common.serialization.StringDeserializer",
                "value.deserializer" to "org.apache.kafka.common.serialization.StringDeserializer",
                "group.id" to "$username-consumer",
                "security.protocol" to "SASL_SSL",
                "sasl.mechanism" to "SCRAM-SHA-256",
                "sasl.jaas.config" to jaasCfg
        )

        return DefaultKafkaConsumerFactory(props)
    }

    @Bean
    fun kafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, String> {
        return ConcurrentKafkaListenerContainerFactory<String, String>().apply {
            consumerFactory = consumerFactory()
        }
    }

}