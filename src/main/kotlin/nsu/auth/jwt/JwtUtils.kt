package nsu.auth.jwt

import io.jsonwebtoken.Claims
import nsu.auth.entity.Roles
import java.util.stream.Collectors

//@NoArgsConstructor(access = AccessLevel.PRIVATE)
object JwtUtils {
    fun generate(claims: Claims): JwtAuthentication {
        val jwtInfoToken = JwtAuthentication()
        jwtInfoToken.roles = getRoles(claims)
        jwtInfoToken.fullname = claims.get("fullName", String::class.java)
        jwtInfoToken.email = claims.subject
        return jwtInfoToken
    }

    private fun getRoles(claims: Claims): Set<Roles?> {
        val roles: List<String> = claims.get("roles", List::class.java) as List<String>
        return roles.stream().map { role: String? -> Roles.valueOf(role!!) }.collect(Collectors.toSet())
    }
}
