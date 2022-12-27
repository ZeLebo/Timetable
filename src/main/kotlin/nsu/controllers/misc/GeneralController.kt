package nsu.controllers.misc


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

/**
 *  General class for research tasks
 * */
@Controller
class GeneralController {

    /**
     * Method which runs when user is trying to get through '/sayHello' path and sends html with hello message to the user
     *
     * @return html file with hello message
     * */
    @GetMapping(
        "/sayHello"
    )
    fun sayHello(@RequestParam name: String, model: Model): String {
        model.addAttribute(name)
        return "hello.html"
    }

    /**
     * Method which runs when user is trying to get through '/showButton' path and shows complex html with different buttons,dropdown lists
     *
     * @return html file with complex html file
     * */
    @GetMapping("/showButton")
    fun showButton(): String {
        return "listsExample.html"
    }

    /**
     *  Method which runs when user is trying to get through '/stocks/{symbol}' path and sends dynamical changing page of stocks
     *
     *  @param symbol - name of the stock
     *  @return dynamically changeable page
     * */
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

/**
 * Custom error message error
 *
 * @param message error message
 * */
class ErrorResponse(val message: String) {
    override fun toString(): String {
        return "{\"error\": \"$this.message\"}"
    }
}

/**
 * Data class for stock
 *
 * @param symbol - name of stock
 * @param price - price of stock
 * @param time - current time
 * */
data class StockPrice(
    val symbol: String,
    val price: Double,
    val time: LocalDateTime
)