package br.com.dias.rest_with_spring_boot_and_java.service;

import br.com.dias.rest_with_spring_boot_and_java.dto.security.AccountCredentialsDTO;
import br.com.dias.rest_with_spring_boot_and_java.dto.security.TokenDTO;
import br.com.dias.rest_with_spring_boot_and_java.repository.UserRepository;
import br.com.dias.rest_with_spring_boot_and_java.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserRepository repository;

    public ResponseEntity<TokenDTO> signIn (AccountCredentialsDTO credentials){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        credentials.getUserName(),
                        credentials.getPassword()
                )
        );

        var user = repository.findByUserName(credentials.getUserName());
        if(user == null) throw new UsernameNotFoundException("Username "
                + credentials.getUserName() + "not found");
        var tokenResponse = tokenProvider.createAccessToken(
                credentials.getUserName(),
                user.getRoles()
        );
        return ResponseEntity.ok(tokenResponse);
    }
}