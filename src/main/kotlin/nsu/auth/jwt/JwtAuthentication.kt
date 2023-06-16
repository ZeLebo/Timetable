package nsu.auth.jwt

import nsu.auth.entity.Roles
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority


class JwtAuthentication : Authentication {
    var authenticated = false
    private set

    var email: String? = null
    var fullname: String? = null
    var roles: Set<Roles?>? = null
    override fun getAuthorities(): Collection<GrantedAuthority?> {
        return roles as Collection<GrantedAuthority?>
    }

    override fun getCredentials(): Any? {
        return null
    }

    override fun getDetails(): Any {
        return email!!
    }

    override fun getPrincipal(): Any {
        return fullname!!
    }

    override fun isAuthenticated(): Boolean {
        return authenticated
    }

    @Throws(IllegalArgumentException::class)
    override fun setAuthenticated(isAuthenticated: Boolean) {
        authenticated = isAuthenticated
    }

    override fun getName(): String {
        return fullname!!
    }


}
