package nsu.controllers


import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import java.time.Duration
import java.time.LocalDateTime
import java.util.concurrent.ThreadLocalRandom

@Controller
class GeneralController {
    // method that tells hello to user
    @GetMapping(
        "/sayHello"
    )
    fun sayHello (@RequestParam name: String, model: Model): String{
        model.addAttribute(name)
        return "hello.html"
    }

    @GetMapping("/showButton")
    fun showButton(): String {
        return "listsExample.html"
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