package org.fludland.api.clients;

import org.fludland.file.ImageDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "file-service", url = "${api.fileservice.host}")
public interface FileServiceClient {
    @GetMapping(path = "/{id}/properties")
    ImageDto getImage(@PathVariable Long id);
}
