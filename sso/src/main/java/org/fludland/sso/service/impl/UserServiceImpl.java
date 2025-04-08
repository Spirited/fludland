package org.fludland.sso.service.impl;

import org.fludland.sso.dtos.UserDetailsDto;
import org.fludland.sso.entities.User;
import org.fludland.sso.exceptions.UserNotFoundException;
import org.fludland.sso.repository.UserRepository;
import org.fludland.sso.service.UserService;
import org.fludland.sso.utils.TokenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final TokenUtils tokenUtils;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    public UserServiceImpl(
            final UserRepository userRepository,
            final TokenUtils tokenUtils
    ) {
        this.userRepository = userRepository;
        this.tokenUtils = tokenUtils;
    }

    @Override
    public UserDetailsDto getUserDetails(final String token) {
        Long userIdFromToken = tokenUtils.extractUserIdFromToken(token);

        User user = userRepository.findById(userIdFromToken).orElseThrow(UserNotFoundException::new);

        LOGGER.info("Found user by ID={}", user.getId());
        return convertUserToUserDetailsDto(user);
    }

    @Override
    public UserDetailsDto getUserDetails(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        return convertUserToUserDetailsDto(user);
    }

    private static UserDetailsDto convertUserToUserDetailsDto(final User user) {
        return new UserDetailsDto(
                user.getId(),
                user.getUsername(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}
