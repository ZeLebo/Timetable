package nsu.auth;

import io.jsonwebtoken.Claims;
import nsu.auth.JwtAuthentication;
import nsu.auth.Roles;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

//@NoArgsConstructor(access = AccessLevel.PRIVATE)

public final class JwtUtils {

    public static JwtAuthentication generate(Claims claims) {
        final JwtAuthentication jwtInfoToken = new JwtAuthentication();
        jwtInfoToken.setRoles(getRoles(claims));
        jwtInfoToken.setFullname(claims.get("fullName", String.class));
        jwtInfoToken.setEmail(claims.getSubject());
        return jwtInfoToken;
    }

    private static Set<Roles> getRoles(Claims claims) {
        final List<String> roles = claims.get("roles", List.class);
        return roles.stream()
                .map(Roles::valueOf)
                .collect(Collectors.toSet());
    }

}
