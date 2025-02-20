package org.fludland.api.clients;

import org.fludland.common.SuccessResponse;
import org.fludland.userservcie.friends.FriendsDto;
import org.fludland.userservcie.profile.CreateProfileDto;
import org.fludland.userservcie.profile.OriginalProfileDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "user-service", url = "${api.user-service.host}")
public interface UserServiceClient {
    @PostMapping(path = "/profiles")
    CreateProfileDto createProfile(CreateProfileDto createProfileDto);

    @GetMapping(path = "/profiles/{userId}")
    OriginalProfileDto getProfile(@PathVariable String userId);

    @PostMapping("/profiles/friends/request")
    SuccessResponse<String> sendRequestToConnect(
            @RequestParam("userId") final Long userId,
            @RequestParam("friendId") Long friendId
    );

    @PostMapping("/profiles/friends")
    SuccessResponse<String> addFriendToUser(
            @RequestParam("userId") final Long userId,
            @RequestParam("friendId") Long friendId
    );

    @GetMapping("/profiles/friends")
    SuccessResponse<FriendsDto> getUserFriends(@RequestParam final Long userId);
}
