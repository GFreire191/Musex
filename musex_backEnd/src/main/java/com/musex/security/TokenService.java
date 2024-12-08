package com.musex.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.musex.entities.User.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class TokenService {

    @Value("{api.security.token.secret}")
    private String secret;

    public String generateToken(User user){
        try{
            Algorithm algorithm =  Algorithm.HMAC256(secret);
            String token = JWT.create().withIssuer("auth-api-musex")
                    .withSubject(user.getUsername())
                    .withClaim("userId", user.getId())
                    .withExpiresAt(generateExpirationDate())
                    .sign(algorithm);
            return token;
        }catch (JWTCreationException exception){
            throw new RuntimeException("Error while generating token",exception);

        }
    }

    // Update the validateToken method to return both the username and user ID
    public Map<String, String> validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            var verifier = JWT.require(algorithm).withIssuer("auth-api-musex").build();
            var decodedJWT = verifier.verify(token);
            Map<String, String> userDetails = new HashMap<>();
            userDetails.put("username", decodedJWT.getSubject());
            userDetails.put("userId", decodedJWT.getClaim("userId").asString());
            return userDetails;
        } catch (JWTVerificationException exception) {
            return Collections.emptyMap();
        }
    }

    private Instant generateExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.UTC);
    }
}
