package com.suiteonix.schoolpaz.file.internal;

import com.suiteonix.schoolpaz.file.FileService;
import io.minio.*;
import io.minio.errors.*;
import io.minio.messages.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
class MinioFileServiceImpl implements FileService {

    private static final Logger logger = LoggerFactory.getLogger(MinioFileServiceImpl.class);
    private static final List<String> ALLOWED_IMAGE_TYPES = Arrays.asList(
            "image/jpeg", "image/png", "image/gif", "image/webp", "image/svg+xml"
    );

    private final MinioClient minioClient;

    @Value("${minio.bucketName}")
    private String bucketName;

    @Value("${minio.endpoint}")
    private String minioEndpoint;

    @Autowired
    public MinioFileServiceImpl(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    @Override
    public String uploadFile(MultipartFile file, String fileName) {
        logger.info("Uploading file: {}", fileName);
        try {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(fileName)
                            .stream(file.getInputStream(), file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build()
            );
            logger.info("File uploaded successfully: {}", fileName);
            return getFileUrl(fileName);
        } catch (Exception e) {
            logger.error("Error uploading file: {}", fileName, e);
            throw new RuntimeException("Error uploading file", e);
        }
    }

    @Override
    public String uploadImage(MultipartFile image, String fileName) {
        logger.info("Uploading image: {}", fileName);
        validateImageFile(image);
        return uploadFile(image, "images/" + fileName);
    }

    @Override
    public String updateFile(MultipartFile file, String fileName) {
        logger.info("Updating file: {}", fileName);
        if (!fileExists(fileName)) {
            logger.warn("File does not exist, creating new file: {}", fileName);
        }
        return uploadFile(file, fileName);
    }

    @Override
    public String updateImage(MultipartFile image, String fileName) {
        logger.info("Updating image: {}", fileName);
        validateImageFile(image);
        String imagePath = "images/" + fileName;
        if (!fileExists(imagePath)) {
            logger.warn("Image does not exist, creating new image: {}", imagePath);
        }
        return uploadFile(image, imagePath);
    }

    @Override
    public boolean deleteFile(String fileName) {
        logger.info("Deleting file: {}", fileName);
        try {
            minioClient.removeObject(
                    RemoveObjectArgs.builder()
                            .bucket(bucketName)
                            .object(fileName)
                            .build()
            );
            logger.info("File deleted successfully: {}", fileName);
            return true;
        } catch (Exception e) {
            logger.error("Error deleting file: {}", fileName, e);
            return false;
        }
    }

    @Override
    public InputStream getFile(String fileName) {
        logger.info("Getting file: {}", fileName);
        try {
            return minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(bucketName)
                            .object(fileName)
                            .build()
            );
        } catch (Exception e) {
            logger.error("Error getting file: {}", fileName, e);
            throw new RuntimeException("Error getting file", e);
        }
    }

    @Override
    public boolean fileExists(String fileName) {
        logger.info("Checking if file exists: {}", fileName);
        try {
            minioClient.statObject(
                    StatObjectArgs.builder()
                            .bucket(bucketName)
                            .object(fileName)
                            .build()
            );
            return true;
        } catch (ErrorResponseException e) {
            if (e.errorResponse().code().equals("NoSuchKey") || e.errorResponse().code().equals("NoSuchObject")) {
                return false;
            }
            logger.error("Error checking if file exists: {}", fileName, e);
            throw new RuntimeException("Error checking if file exists", e);
        } catch (Exception e) {
            logger.error("Error checking if file exists: {}", fileName, e);
            throw new RuntimeException("Error checking if file exists", e);
        }
    }

    @Override
    public List<String> listFiles() {
        logger.info("Listing all files");
        List<String> fileNames = new ArrayList<>();
        try {
            Iterable<Result<Item>> results = minioClient.listObjects(
                    ListObjectsArgs.builder()
                            .bucket(bucketName)
                            .build()
            );
            for (Result<Item> result : results) {
                Item item = result.get();
                fileNames.add(item.objectName());
            }
            return fileNames;
        } catch (Exception e) {
            logger.error("Error listing files", e);
            throw new RuntimeException("Error listing files", e);
        }
    }

    private String getFileUrl(String fileName) {
        return minioEndpoint + "/" + bucketName + "/" + fileName;
    }

    private void validateImageFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Image file cannot be empty");
        }

        String contentType = file.getContentType();
        if (contentType == null || !ALLOWED_IMAGE_TYPES.contains(contentType)) {
            throw new IllegalArgumentException("Invalid image format. Allowed formats: JPEG, PNG, GIF, WebP, SVG");
        }
    }
}