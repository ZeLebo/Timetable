package nsu.auth.requests

import nsu.auth.entity.Roles

class RolesRequest {
    val login: String = ""
    val roles: Set<Roles> = HashSet()
}