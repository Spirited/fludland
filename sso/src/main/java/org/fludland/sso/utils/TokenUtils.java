package org.fludland.sso.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Provides token utils like create, validate, etc.
 * Used example from <a href=https://medium.com/@salvipriya97/jwt-implementation-example-f36061be1405>medium article</a>
 */
@Component
public final class TokenUtils {

    private static final String SECRET = "mySecretKey";  // Secret key for signing JWT
    private static final long EXPIRATION_TIME = 86400000;

    public String generateJWT(Long userId, String username) {

        return JWT.create()
                .withSubject(username)  // Username or user ID
                .withClaim("userid", userId)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))  // Expiration time
                .sign(Algorithm.HMAC256(SECRET));
    }

    // Method to validate the JWT token
    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);  // If token is valid, decode it
            return decodedJWT.getSubject();  // Return the username from token
        } catch (JWTVerificationException e) {
            return null;  // Invalid token
        }
    }

}
