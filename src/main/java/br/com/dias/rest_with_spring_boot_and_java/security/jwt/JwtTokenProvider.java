package br.com.dias.rest_with_spring_boot_and_java.security.jwt;

import br.com.dias.rest_with_spring_boot_and_java.dto.security.TokenDTO;
import br.com.dias.rest_with_spring_boot_and_java.exception.InvalidJwtAutheticationException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
public class JwtTokenProvider {

    @Value("${security.jwt.token.secret-key:secret}")
    private String secretKey = "secret";

    @Value("${security.jwt.token.expire-lenght:3600000}")
    private long validityInMilliseconds = 3600000;// Equivale a 1h.

    @Autowired
    private UserDetailsService userDetailsService;

    Algorithm algorithm = null;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        algorithm = Algorithm.HMAC256(secretKey.getBytes());
    }

    public TokenDTO createAccessToken(String userName, List<String> roles) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);
        String accessToken = getAccessToken(userName, roles, now, validity);
        String refreshToken = getRefreshToken(userName, roles, now);

        return new TokenDTO(userName, true, now, validity, accessToken, refreshToken);
    }

    private String getAccessToken(String userName, List<String> roles, Date now, Date validity) {
        String issuerUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .build().toString(); // pega o contexto que está rodando localhost 8080 e determina qual site o token está sendo gerado
        return JWT.create()
                .withClaim("roles", roles)
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .withSubject(userName)
                .withIssuer(issuerUrl)
                .sign(algorithm);

    }

    private String getRefreshToken(String userName, List<String> roles, Date now) {

        Date refreshTokenValidity = new Date(now.getTime() + validityInMilliseconds * 3);
        return JWT.create()
                .withClaim("roles", roles)
                .withIssuedAt(now)
                .withExpiresAt(refreshTokenValidity)
                .withSubject(userName)
                .sign(algorithm);
    }

    public Authentication getAuthentication(String token) {
        DecodedJWT decodedJWT = decodedToken(token);
        UserDetails userDetails = this.userDetailsService
                .loadUserByUsername(decodedJWT.getSubject());
        return new UsernamePasswordAuthenticationToken(userDetails,
                "", userDetails.getAuthorities());
    }

    private DecodedJWT decodedToken(String token) {
        Algorithm algorith = Algorithm.HMAC256(secretKey.getBytes());
        JWTVerifier verifier = JWT.require(algorith).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT;
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        if (StringUtils.isEmpty(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring("Bearer".length());
        }
        return null;
    }

    public boolean validateToken(String token) {
        DecodedJWT decodedJWT = decodedToken(token);
        try {
            if (decodedJWT.getExpiresAt().before(new Date())) {
                return false;
            }
        } catch (Exception e) {
            throw new InvalidJwtAutheticationException("Expired or Invalid JWT token");
        }
        return true;
    }
}