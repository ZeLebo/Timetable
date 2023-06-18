package nsu.auth

import nsu.auth.jwt.JwtFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig(private val jwtFilter: JwtFilter) {
    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .httpBasic().disable()
            .csrf().disable()
            .cors().and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeHttpRequests { authz ->
                authz
                    .requestMatchers("/api/v1/auth/login", "/api/v1/auth/token").permitAll()
                    .requestMatchers("/api/v1/auth/register").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .addFilterAfter(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)
            }.build()
    }
    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()
        configuration.allowedHeaders = listOf("*")
        configuration.exposedHeaders = listOf("*")
        configuration.allowedMethods = listOf("GET", "POST", "UPDATE", "PUT", "DELETE", "OPTIONS", "PATCH")
        configuration.allowCredentials = true
        configuration.maxAge = 1800L
        val urlConfiguration = UrlBasedCorsConfigurationSource()
        urlConfiguration.registerCorsConfiguration("/", configuration)

        return urlConfiguration
    }
}
