package nsu.controllers.routers


import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import java.time.Duration

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime
import java.util.concurrent.ThreadLocalRandom

@RestController
class GeneralRouter {
    // method that tells hello to user
    @GetMapping("/hello")
    fun hello(@RequestParam(value = "name", defaultValue = "world") name: String): String {
        return String.format("Hello %s!", name)
    }


    @GetMapping(
        value = ["/stocks/{symbol}"],
        produces = [MediaType.TEXT_EVENT_STREAM_VALUE]
    )
    fun prices(@PathVariable symbol: String): Flux<StockPrice> {
        return Flux.interval(Duration.ofSeconds(1))
            .map { StockPrice(symbol, randomStockPrice(), LocalDateTime.now()) }
    }
    private fun randomStockPrice(): Double {
        return ThreadLocalRandom.current().nextDouble(100.0)
    }
}

class ErrorResponse(val message: String) {
    override fun toString() : String {
        return "{\"error\": \"$this.message\"}"
    }
}

data class StockPrice(
    val symbol: String,
    val price: Double,
    val time: LocalDateTime
)