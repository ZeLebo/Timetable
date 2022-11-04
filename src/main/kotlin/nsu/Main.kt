package nsu

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TimetableApplication

fun main(args: Array<String>) {
    runApplication<TimetableApplication>(*args)
}
