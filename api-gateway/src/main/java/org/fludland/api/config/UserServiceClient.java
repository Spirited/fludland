package org.fludland.api.config;

import org.fludland.userservcie.CreateProfileDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "user-service", url = "${api.user-service.host}")
public interface UserServiceClient {
    @PostMapping(path = "/profiles")
    CreateProfileDto createProfile(CreateProfileDto createProfileDto);
}
