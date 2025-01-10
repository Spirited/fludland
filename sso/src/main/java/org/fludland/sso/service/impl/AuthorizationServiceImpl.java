package org.fludland.sso.service.impl;

import org.fludland.sso.dtos.AuthorizationDto;
import org.fludland.sso.dtos.SuccessfulResult;
import org.fludland.sso.dtos.LoginCreateDto;
import org.fludland.sso.entities.Profile;
import org.fludland.sso.entities.User;
import org.fludland.sso.exceptions.UsernameAlreadyExistsException;
import org.fludland.sso.exceptions.WrongLoginOrPasswordException;
import org.fludland.sso.repository.ProfileRepository;
import org.fludland.sso.repository.UserRepository;
import org.fludland.sso.service.AuthorizationService;
import org.fludland.sso.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final TokenUtils tokenUtils;

    @Autowired
    public AuthorizationServiceImpl(final UserRepository userRepository,
                                    final ProfileRepository profileRepository,
                                    final TokenUtils tokenUtils) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
        this.tokenUtils = tokenUtils;
    }

    @Override
    public SuccessfulResult login(AuthorizationDto authorizationDto) {
        User user = userRepository.findByUsername(authorizationDto.getUsername()).orElseThrow(WrongLoginOrPasswordException::new);

        if (!authorizationDto.getPassword().equals(user.getPassword())) {
            throw new WrongLoginOrPasswordException();
        }

        return new SuccessfulResult(tokenUtils.generateJWT(authorizationDto.getUsername()));
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

        Profile profile = new Profile();
        profile.setUser(user);

        profileRepository.save(profile);

        return new SuccessfulResult(tokenUtils.generateJWT(user.getUsername()));
    }

    @Override
    public void delete(String username) {   //will be implemented soon
        throw new UnsupportedOperationException();
    }

    @Override
    public void logout(String username) {   //will be implemented soon
        throw new UnsupportedOperationException();
    }
}
