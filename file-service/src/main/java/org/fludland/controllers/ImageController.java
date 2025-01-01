package org.fludland.controllers;

import org.fludland.file.ImageDto;
import org.fludland.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;

@RestController
@RequestMapping(path = "/images")
public class ImageController {
    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> addImage(@RequestParam("image") MultipartFile file) {
        return ResponseEntity.ok(imageService.addImage(file));
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable long id) {
        imageService.deleteImage(id);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(IMAGE_JPEG_VALUE))
                .body(imageService.getImageData(id));
    }

    @GetMapping(path = "/{id}/properties")
    public ResponseEntity<ImageDto> getImageProperties(@PathVariable Long id) {
        return ResponseEntity.ok(imageService.getImage(id));
    }
}
