package nsu

import org.springframework.cache.CacheManager
import org.springframework.cache.concurrent.ConcurrentMapCache
import org.springframework.cache.support.SimpleCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RequestCacheConfig {
    @Bean
    fun cacheManager(): SimpleCacheManager {
        val cacheManager: SimpleCacheManager = SimpleCacheManager()
        // allow caching for MailRequest and LoginRequest
        cacheManager.setCaches(listOf(ConcurrentMapCache("mails"), ConcurrentMapCache("logins")))
        return cacheManager
    }

    @Bean
    fun httpRequestCache(): CacheManager {
        return cacheManager()
    }

}