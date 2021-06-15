package com.webproject.restaurant.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;

@Service
@Slf4j
public class ImageStorageService {

    private static final Path imagesFolderPath =
            Paths.get("src\\main\\resources\\static\\images").toAbsolutePath().normalize();

    public String saveImage(MultipartFile image) {

        if (!Objects.equals(image.getContentType(), MediaType.IMAGE_JPEG_VALUE)) {
            throw new RuntimeException("The image must be jpeg");
        }

        String imageNameToStore = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) + ".jpg";
        Path imagePath = Paths.get(imagesFolderPath + "\\" + imageNameToStore);

        try {
            Files.copy(image.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Could not store the file");
        }

        return imageNameToStore;
    }

    public String updateImage(String oldImageName, MultipartFile newImage) {
        String newImageName = this.saveImage(newImage);
        Path oldImagePath = Paths.get(imagesFolderPath + "\\" + oldImageName);

        try {
            Files.delete(oldImagePath);
        } catch (IOException e) {
            log.error("The image with name " + oldImageName + " cannot be removed");
        }

        return newImageName;
    }
}
