package nsu.auth.controllers

import nsu.auth.entity.User
import nsu.auth.jwt.JwtResponse
import nsu.auth.requests.*
import nsu.auth.service.AuthService
import nsu.auth.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/v1/auth")
class AuthController(
    private val authService: AuthService,
    private val userService: UserService
) {
    // creates default user with student role
    // everybody can do this
    @PostMapping("register")
    fun registerUser(@RequestBody user: UserRequest): ResponseEntity<*>{
        return try {
            ResponseEntity.ok(userService.addUser(user))
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(e.message)
        }
    }

    // only admin can do this endpoint, have to add roles to the person
    /*
    * "roles" : ["STUDENT", "TEACHER"]
    * */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("register/admin")
    fun registerUserByAdmin(@RequestBody user: User): ResponseEntity<*> {
        return try {
            ResponseEntity.ok(userService.addUser(user))
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(e.message)
        }
    }

    // delete the user
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("register")
    fun deleteUser(@RequestBody user: UserDeleteRequest): ResponseEntity<*> {
        return try {
            ResponseEntity.ok(userService.deleteUserByLogin(user.login))
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(e.message)
        }
    }

    /*
    * get a new pair of access and refresh token
    * access token is used to send requests
    * refresh - to update the token
    * */

    @CrossOrigin
    @PostMapping("login")
    fun login(@RequestBody authRequest: JwtRequest?): ResponseEntity<*> {
        return try {
           ResponseEntity.ok(authService.login(authRequest!!))
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(e.message)
        }
    }

    /*
    * Update the access token, when sending refresh one in body
    * */
    @PostMapping("token")
    fun getNewAccessToken(@RequestBody request: RefreshJwtRequest): ResponseEntity<JwtResponse> {
        val token = request.refreshToken?.let { authService.getAccessToken(it) }
        return ResponseEntity.ok(token)
    }

    @PostMapping("refresh")
    fun getNewRefreshToken(@RequestBody request: RefreshJwtRequest): ResponseEntity<JwtResponse> {
        val token = request.refreshToken?.let { authService.refresh(it) }
        return ResponseEntity.ok(token)
    }
}
