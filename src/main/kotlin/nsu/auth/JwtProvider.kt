package nsu.auth

import io.jsonwebtoken.*
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.lang.NonNull
import org.springframework.stereotype.Component
import java.security.Key
import java.security.SignatureException
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*
import javax.crypto.SecretKey

@Component
class JwtProvider(
    @Value("\${jwt.secret.access}") jwtAccessSecret: String?,
    @Value("\${jwt.secret.refresh}") jwtRefreshSecret: String?
) {
    private val jwtAccessSecret: SecretKey
    private val jwtRefreshSecret: SecretKey

    init {
        this.jwtAccessSecret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtAccessSecret))
        this.jwtRefreshSecret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtRefreshSecret))
    }

    fun generateAccessToken(@NonNull user: User): String {
        val now = LocalDateTime.now()
        val accessExpirationInstant = now.plusMinutes(5).atZone(ZoneId.systemDefault()).toInstant()
        val accessExpiration = Date.from(accessExpirationInstant)
        return Jwts.builder()
            .setSubject(user.getLogin())
            .setExpiration(accessExpiration)
            .signWith(jwtAccessSecret)
            .claim("roles", user.getRoles())
            .claim("fullName", user.fullname)
            .compact()
    }

    fun generateRefreshToken(@NonNull user: User): String {
        val now = LocalDateTime.now()
        val refreshExpirationInstant = now.plusDays(30).atZone(ZoneId.systemDefault()).toInstant()
        val refreshExpiration = Date.from(refreshExpirationInstant)
        return Jwts.builder()
            .setSubject(user.getLogin())
            .setExpiration(refreshExpiration)
            .signWith(jwtRefreshSecret)
            .compact()
    }

    fun validateAccessToken(@NonNull accessToken: String): Boolean {
        return validateToken(accessToken, jwtAccessSecret)
    }

    fun validateRefreshToken(@NonNull refreshToken: String): Boolean {
        return validateToken(refreshToken, jwtRefreshSecret)
    }

    private fun validateToken(@NonNull token: String, @NonNull secret: Key): Boolean {
        try {
            Jwts.parserBuilder()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
            return true
        } catch (expEx: ExpiredJwtException) {
            println("Token expired$expEx")
        } catch (unsEx: UnsupportedJwtException) {
            println("Unsupported jwt$unsEx")
        } catch (mjEx: MalformedJwtException) {
            println("malformed$mjEx")
        } catch (sEx: SignatureException) {
            println("Invalid signature $sEx")
        } catch (e: Exception) {
            println("Invalid toke$e")
        }
        return false
    }

    fun getAccessClaims(@NonNull token: String): Claims {
        return getClaims(token, jwtAccessSecret)
    }

    fun getRefreshClaims(@NonNull token: String): Claims {
        return getClaims(token, jwtRefreshSecret)
    }

    private fun getClaims(@NonNull token: String, @NonNull secret: Key): Claims {
        return Jwts.parserBuilder()
            .setSigningKey(secret)
            .build()
            .parseClaimsJws(token)
            .body
    }
}
