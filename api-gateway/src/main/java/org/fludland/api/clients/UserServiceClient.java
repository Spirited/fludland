package org.fludland.api.clients;

import org.fludland.userservcie.profile.CreateProfileDto;
import org.fludland.userservcie.profile.OriginalProfileDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "user-service", url = "${api.user-service.host}")
public interface UserServiceClient {
    @PostMapping(path = "/profiles")
    CreateProfileDto createProfile(CreateProfileDto createProfileDto);

    @GetMapping(path = "/profiles/{userId}")
    OriginalProfileDto getProfile(@PathVariable String userId);
}
