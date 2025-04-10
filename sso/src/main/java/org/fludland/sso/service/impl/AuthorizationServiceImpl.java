package org.fludland.sso.service.impl;

import org.fludland.sso.dtos.LoginRequestDto;
import org.fludland.sso.dtos.SuccessfulResult;
import org.fludland.sso.dtos.LoginCreateDto;
import org.fludland.sso.entities.User;
import org.fludland.sso.enums.UserAccountStatus;
import org.fludland.sso.enums.UserOnlineStatus;
import org.fludland.sso.exceptions.UserAlreadyOfflineException;
import org.fludland.sso.exceptions.UserNotFoundException;
import org.fludland.sso.exceptions.UsernameAlreadyExistsException;
import org.fludland.sso.exceptions.WrongLoginOrPasswordException;
import org.fludland.sso.repository.UserRepository;
import org.fludland.sso.service.AuthorizationService;
import org.fludland.sso.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {
    private final UserRepository userRepository;
    private final TokenUtils tokenUtils;

    @Autowired
    public AuthorizationServiceImpl(final UserRepository userRepository,
                                    final TokenUtils tokenUtils
    ) {
        this.userRepository = userRepository;
        this.tokenUtils = tokenUtils;
    }

    @Override
    public SuccessfulResult login(LoginRequestDto loginRequestDto) {
        User user = userRepository.findByUsername(loginRequestDto.getUsername()).orElseThrow(WrongLoginOrPasswordException::new);

        if (!loginRequestDto.getPassword().equals(user.getPassword())) {
            throw new WrongLoginOrPasswordException();
        }

        user.setLastLogin(LocalDateTime.now());
        user.setUserOnlineStatus(UserOnlineStatus.ONLINE);

        userRepository.save(user);

        return new SuccessfulResult(tokenUtils.generateJWT(user.getId(), loginRequestDto.getUsername()));
    }

    @Transactional
    @Override
    public SuccessfulResult register(LoginCreateDto login) {
        Optional<User> byUsername = userRepository.findByUsername(login.getUsername());

        if (byUsername.isPresent()) {
            throw new UsernameAlreadyExistsException("Username " + login.getUsername() + " already exists");
        }

        User user = new User();
        user.setUsername(login.getUsername());
        user.setPassword(login.getPassword());
        user.setAccountStatus(UserAccountStatus.REGISTERED);
        user.setUserOnlineStatus(UserOnlineStatus.ONLINE);

        User saved = userRepository.save(user);

        return new SuccessfulResult(tokenUtils.generateJWT(saved.getId(), saved.getUsername()));
    }

    @Override
    public void delete(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);

        user.setUserOnlineStatus(UserOnlineStatus.OFFLINE);
        user.setAccountStatus(UserAccountStatus.DELETED);
        user.setUpdatedAt(LocalDateTime.now());

        userRepository.save(user);
    }

    @Override
    public void logout(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);

        if (user.getUserOnlineStatus() == UserOnlineStatus.OFFLINE) {
            throw new UserAlreadyOfflineException();
        }
        user.setUserOnlineStatus(UserOnlineStatus.OFFLINE);
        userRepository.save(user);
    }

    @Override
    public void changePassword(String username) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void verifyEmail(String username) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void rememberMe(String username) {
        throw new UnsupportedOperationException();
    }
}
