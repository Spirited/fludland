package org.fludland.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

public final class JWTUtils {
    private JWTUtils() {}

    public static String extractUserNameFromToken(String token) {
        DecodedJWT decodedJWT = JWT.decode(token);

        return decodedJWT.getClaims().get("sub").asString();
    }

    public static Long extractUserIdFromToken(String token) {
        DecodedJWT decodedJWT = JWT.decode(token);
        return decodedJWT.getClaims().get("userid").asLong();
    }
}
