package nsu.auth.controllers

import nsu.auth.requests.RolesRequest
import nsu.auth.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/roles")
class RolesController(
    private val userService: UserService
) {
    // add new roles to user
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("")
    fun addRolesToUser(@RequestBody req: RolesRequest): ResponseEntity<*> {
        return try {
            ResponseEntity.ok(userService.addRolesToUserByLogin(req.login, req.roles))
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(e.message)
        }
    }

    // remove roles from user
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("")
    fun removeRolesFromUser(@RequestBody req: RolesRequest): ResponseEntity<*> {
        return try {
            ResponseEntity.ok(userService.removeRolesFromUserByLogin(req.login, req.roles))
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(e.message)
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("")
    fun updateRolesForUser(@RequestBody req: RolesRequest): ResponseEntity<*> {
        return try {
            ResponseEntity.ok(userService.updateRolesForUserByLogin(req.login, req.roles))
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(e.message)
        }
    }
}