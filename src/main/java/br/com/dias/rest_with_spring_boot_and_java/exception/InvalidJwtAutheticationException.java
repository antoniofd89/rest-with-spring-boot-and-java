package br.com.dias.rest_with_spring_boot_and_java.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class InvalidJwtAutheticationException extends AuthenticationException {
    public InvalidJwtAutheticationException(String message) {
        super(message);
    }
}
