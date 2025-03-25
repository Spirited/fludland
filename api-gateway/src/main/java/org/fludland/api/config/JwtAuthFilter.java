package org.fludland.api.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.fludland.api.clients.SSOClient;
import org.fludland.common.ErrorType;
import org.fludland.common.ErrorResponse;
import org.fludland.sso.dtos.UserDetailsDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Set;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    private final ObjectMapper objectMapper;
    private final SSOClient ssoClient;

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthFilter.class);
    private static final Set<String> allowedURLs = Set.of("/login", "/sign-up");
    private static final Set<String> exceptURL = Set.of("/swagger", "/v3", "/v3/api-docs");

    @Autowired
    public JwtAuthFilter(final ObjectMapper objectMapper, final SSOClient ssoClient) {
        this.objectMapper = objectMapper;
        this.ssoClient = ssoClient;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain
    ) throws ServletException, IOException {
        String requestURL = request.getRequestURL().toString();
        LOGGER.info("Request URI: {}", requestURL);

        boolean isAllowed = allowedURLs.stream().anyMatch(requestURL::contains);
        boolean isExcepted = exceptURL.stream().anyMatch(requestURL::contains);

        if (!isAllowed && !isExcepted) {

            String authorization = request.getHeader("Authorization");

            if (StringUtils.isBlank(authorization)) {
                LOGGER.warn("The request missing Authorization header");
                setErrorResponse(HttpStatus.UNAUTHORIZED, response, ErrorType.NOT_AUTHORIZED_REQUEST);
                return;
            }

            if (authorization.startsWith("Bearer ")) {
                String token = authorization.substring(7);
                UserDetailsDto user = ssoClient.getUser(token);
                if (user != null) {
                    LOGGER.info("Successfully authenticated user: {}", user.getUsername());

                    request.setAttribute("userId", user.getUserId());
                } else {
                    LOGGER.warn("User not found");
                    setErrorResponse(HttpStatus.NOT_FOUND, response, ErrorType.MESSAGE_NOT_FOUND);
                    return;
                }
            } else {
                LOGGER.warn("Missed Bearer");
                setErrorResponse(HttpStatus.UNAUTHORIZED, response, ErrorType.NOT_AUTHORIZED_REQUEST);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    private void setErrorResponse(HttpStatus status, HttpServletResponse response, ErrorType errorCode){
        response.setStatus(status.value());
        response.setContentType("application/json");
        ErrorResponse apiError = new ErrorResponse(errorCode);
        try {
            String json = objectMapper.writeValueAsString(apiError);
            response.getWriter().write(json);
        } catch (IOException e) {
            LOGGER.warn("The response hadn't been written", e);
        }
    }
}
