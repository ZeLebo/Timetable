package nsu.auth.service

import nsu.auth.jwt.JwtAuthentication
import nsu.auth.jwt.JwtProvider
import nsu.auth.requests.JwtRequest
import nsu.auth.jwt.JwtResponse
import org.springframework.lang.NonNull
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
//@RequiredArgsConstructor
class AuthService(
    private val userService: UserService,
    private val jwtProvider: JwtProvider
) {
    private val refreshStorage: MutableMap<String, String> = HashMap()

    fun login(@NonNull authRequest: JwtRequest): JwtResponse {
        val user = userService.getByLogin(authRequest.login)
            .orElseThrow { RuntimeException("Пользователь не найден") }
        return if (user.password == authRequest.password) {
            val accessToken = jwtProvider.generateAccessToken(user)
            val refreshToken = jwtProvider.generateRefreshToken(user)
            refreshStorage[user.login] = refreshToken
            JwtResponse(accessToken, refreshToken)
        } else {
            throw RuntimeException("Неправильный пароль")
        }
    }

    fun getAccessToken(@NonNull refreshToken: String): JwtResponse {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            val claims = jwtProvider.getRefreshClaims(refreshToken)
            val login = claims.subject
            val saveRefreshToken = refreshStorage[login]
            if (saveRefreshToken != null && saveRefreshToken == refreshToken) {
                val user = userService.getByLogin(login)
                    .orElseThrow { RuntimeException("Пользователь не найден") }
                val accessToken = jwtProvider.generateAccessToken(user)
                return JwtResponse(accessToken, null)
            }
        }
        return JwtResponse(null, null)
    }

    fun refresh(@NonNull refreshToken: String): JwtResponse {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            val claims = jwtProvider.getRefreshClaims(refreshToken)
            val login = claims.subject
            val saveRefreshToken = refreshStorage[login]
            if (saveRefreshToken != null && saveRefreshToken == refreshToken) {
                val user = userService.getByLogin(login)
                    .orElseThrow { RuntimeException("Пользователь не найден") }
                val accessToken = jwtProvider.generateAccessToken(user)
                val newRefreshToken = jwtProvider.generateRefreshToken(user)
                refreshStorage[user.login] = newRefreshToken
                return JwtResponse(accessToken, newRefreshToken)
            }
        }
        throw RuntimeException("Невалидный JWT токен")
    }

    val authInfo: JwtAuthentication
        get() = SecurityContextHolder.getContext().authentication as JwtAuthentication
}
