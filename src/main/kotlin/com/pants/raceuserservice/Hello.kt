package com.pants.raceuserservice

import javax.persistence.*

@Entity
data class Hello(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,

        val greeting: String = ""
)