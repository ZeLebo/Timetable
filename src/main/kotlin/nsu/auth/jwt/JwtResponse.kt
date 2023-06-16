package nsu.auth.jwt

class JwtResponse(
    val type: String = "Bearer",
    var accessToken: String?,
    var refreshToken: String?
) {
    constructor(access : String?, refresh: String?):
        this(accessToken = access, refreshToken = refresh)



}