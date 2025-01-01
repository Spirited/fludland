package org.fludland.services.impl;

import org.fludland.entities.ImageEntity;
import org.fludland.enums.ImageType;
import org.fludland.exceptions.ImageNotFoundException;
import org.fludland.exceptions.UploadFileBrokenException;
import org.fludland.file.ImageDto;
import org.fludland.repository.ImageRepository;
import org.fludland.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;
    private static final String IMAGE_URL_TEMPLATE = "/images/%s";

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public String addImage(MultipartFile file) {
        ImageEntity image = new ImageEntity();
        image.setFilename(file.getOriginalFilename());
        image.setType(ImageType.getMimeType(file.getContentType()));
        image.setSize(file.getSize());

        try {
            BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(file.getBytes()));
            if (bufferedImage != null) {
                image.setWidth(bufferedImage.getWidth());
                image.setHeight(bufferedImage.getHeight());
            }
            image.setData(file.getBytes());
        } catch (IOException e) {
            throw new UploadFileBrokenException("Could not upload file. The reason is: " + e.getMessage());
        }

        ImageEntity savedImage = imageRepository.save(image);

        return String.format(IMAGE_URL_TEMPLATE, savedImage.getId());
    }

    @Override
    public byte[] getImageData(long id) {
        ImageEntity image = imageRepository.findById(id).orElseThrow(() -> new ImageNotFoundException("Image not found with ID: " + id));

        return image.getData();
    }

    @Override
    public ImageDto getImage(long id) {
        ImageEntity image = imageRepository.findById(id).orElseThrow(() -> new ImageNotFoundException("Image not found with ID: " + id));

        return convertToDto(image);
    }

    @Override
    public void deleteImage(long id) {
        Optional<ImageEntity> imageEntityOptional = imageRepository.findById(id);

        if (imageEntityOptional.isEmpty()) {
            throw new ImageNotFoundException("Image not found or already deleted");
        }

        imageRepository.deleteById(id);
    }

    private static ImageDto convertToDto(ImageEntity image) {
        return new ImageDto(
                image.getId(),
                image.getFilename(),
                String.format(IMAGE_URL_TEMPLATE, image.getId()),
                image.getDuration(),
                image.getWidth(),
                image.getHeight()
        );
    }
}
