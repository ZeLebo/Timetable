package nsu.auth.controllers

import nsu.auth.service.AuthService
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api")
class Controller(private val authService: AuthService) {
    @PreAuthorize("hasAuthority('STUDENT')")
    @GetMapping("hello/student")
    fun helloUser(): ResponseEntity<String> {
        val authInfo = authService.authInfo
        return ResponseEntity.ok("Hello user " + authInfo.principal + "!")
    }

    @PreAuthorize("hasAuthority('TEACHER')")
    @GetMapping("hello/teacher")
    fun helloTeacher(): ResponseEntity<String> {
        val authInfo = authService.authInfo
        return ResponseEntity.ok("Hello user " + authInfo.principal + "!")
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("hello/admin")
    fun helloAdmin(): ResponseEntity<String> {
        val authInfo = authService.authInfo
        return ResponseEntity.ok("Hello admin " + authInfo.principal + "!")
    }
}
