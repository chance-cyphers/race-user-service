package com.pants.raceuserservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RaceUserServiceApplication

fun main(args: Array<String>) {
	runApplication<RaceUserServiceApplication>(*args)
}
