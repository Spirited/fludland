package org.fludland.services;

import org.fludland.file.ImageDto;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    String addImage(MultipartFile file);
    byte[] getImageData(long id);
    void deleteImage(long id);
    ImageDto getImage(long id);
}
