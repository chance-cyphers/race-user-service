package com.pants.raceuserservice

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.sql.DataSource


@RestController
class WinsController(@Autowired val dataSource: DataSource) {

    var counter = 0

    @RequestMapping("/")
    fun hello(): String {
        return "Race api was visited $counter times, lately"
    }

    @RequestMapping("/db")
    fun db(): List<String> {
        try {
            dataSource.connection.use { connection ->
                val stmt = connection.createStatement().apply {
                    executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)")
                    executeUpdate("INSERT INTO ticks VALUES (now())")
                }
                val rs = stmt.executeQuery("SELECT tick FROM ticks")

                val output = mutableListOf<String>()
                while (rs.next()) {
                    output.add("Read from DB: " + rs.getTimestamp("tick"))
                }

                return output.toList()
            }
        } catch (e: Exception) {
            return mutableListOf("error")
        }
    }

}