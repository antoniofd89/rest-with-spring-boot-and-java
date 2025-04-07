package br.com.dias.rest_with_spring_boot_and_java.controllers;

import br.com.dias.rest_with_spring_boot_and_java.dto.security.AccountCredentialsDTO;
import br.com.dias.rest_with_spring_boot_and_java.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Authentication Endpoint")
@RestController
@RequestMapping("/auth/signin")
public class AuthController {

    @Autowired
    private AuthService service;

    @Operation(summary = "Authenticates an user and return a token ")
    @PostMapping("/signin")
    public ResponseEntity<?> singIn(AccountCredentialsDTO credentials) {
        if (credentialsIsInvalid(credentials)) return ResponseEntity.ok(service.signIn(credentials)
                .getBody());

        var token = service.signIn(credentials);

        if (token == null) ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request");
        return ResponseEntity.ok().body(token);
    }

    private static boolean credentialsIsInvalid(AccountCredentialsDTO credentials) {
        return credentials == null ||
                StringUtils.isBlank(credentials.getPassword()) ||
                StringUtils.isBlank(credentials.getUserName());
    }
}
