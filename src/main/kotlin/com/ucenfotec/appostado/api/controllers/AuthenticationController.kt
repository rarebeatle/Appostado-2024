package com.ucenfotec.appostado.api.controllers

import com.ucenfotec.appostado.core.application.common.interfaces.authentication.IAuthenticationService
import com.ucenfotec.appostado.core.application.dtos.user.UserDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth")
class AuthenticationController(
    val authenticationService: IAuthenticationService
) {
    @PostMapping("/sign-up")
    fun signUp(@RequestBody user: UserDto): ResponseEntity<Any> {
        val createdUser = authenticationService.signUp(user);
        val createdResult = createdUser.get();
        return ResponseEntity.ok(createdResult);
    }
}