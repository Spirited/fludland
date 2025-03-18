package org.fludland.api.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.fludland.api.clients.SSOClient;
import org.fludland.sso.dtos.UserDetailsDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Set;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    private final SSOClient ssoClient;

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthFilter.class);
    private static final Set<String> allowedURLs = Set.of("/login", "/sign-up", "/swagger-ui.html");

    @Autowired
    public JwtAuthFilter(SSOClient ssoClient) {
        this.ssoClient = ssoClient;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain
    ) throws ServletException, IOException {
        String requestURl = request.getRequestURL().toString();
        LOGGER.info("Request URI: {}", requestURl);

        boolean isAllowed = allowedURLs.stream().anyMatch(requestURl::contains);

        if (!isAllowed) {

            String authorization = request.getHeader("Authorization");

            if (authorization != null && authorization.startsWith("Bearer ")) {
                String token = authorization.substring(7);
                UserDetailsDto user = ssoClient.getUser(token);
                if (user != null) {
                    LOGGER.info("Successfully authenticated user: {}", user.getUsername());

                    request.setAttribute("userId", user.getUserId());
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
