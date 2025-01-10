package org.fludland.api.config;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "user-service", url = "${api.user-service.host}")
public class UserServiceClient {

}
