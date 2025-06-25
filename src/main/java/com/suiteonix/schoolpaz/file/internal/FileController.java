package com.suiteonix.schoolpaz.file.internal;

import com.suiteonix.schoolpaz.file.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/files")
 class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);
    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("fileName") String fileName) {
        logger.info("Received request to upload file: {}", fileName);
        try {
            String fileUrl = fileService.uploadFile(file, fileName);
            return ResponseEntity.ok(fileUrl);
        } catch (Exception e) {
            logger.error("Error uploading file: {}", fileName, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error uploading file: " + e.getMessage());
        }
    }

    @PostMapping("/upload/image")
    public ResponseEntity<String> uploadImage(
            @RequestParam("image") MultipartFile image,
            @RequestParam("fileName") String fileName) {
        logger.info("Received request to upload image: {}", fileName);
        try {
            String imageUrl = fileService.uploadImage(image, fileName);
            return ResponseEntity.ok(imageUrl);
        } catch (IllegalArgumentException e) {
            logger.error("Invalid image: {}", fileName, e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid image: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Error uploading image: {}", fileName, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error uploading image: " + e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("fileName") String fileName) {
        logger.info("Received request to update file: {}", fileName);
        try {
            String fileUrl = fileService.updateFile(file, fileName);
            return ResponseEntity.ok(fileUrl);
        } catch (Exception e) {
            logger.error("Error updating file: {}", fileName, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating file: " + e.getMessage());
        }
    }

    @PutMapping("/update/image")
    public ResponseEntity<String> updateImage(
            @RequestParam("image") MultipartFile image,
            @RequestParam("fileName") String fileName) {
        logger.info("Received request to update image: {}", fileName);
        try {
            String imageUrl = fileService.updateImage(image, fileName);
            return ResponseEntity.ok(imageUrl);
        } catch (IllegalArgumentException e) {
            logger.error("Invalid image: {}", fileName, e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid image: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Error updating image: {}", fileName, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating image: " + e.getMessage());
        }
    }

    @DeleteMapping("/{fileName}")
    public ResponseEntity<String> deleteFile(@PathVariable String fileName) {
        logger.info("Received request to delete file: {}", fileName);
        boolean deleted = fileService.deleteFile(fileName);
        if (deleted) {
            return ResponseEntity.ok("File deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("File not found or could not be deleted");
        }
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<InputStreamResource> getFile(@PathVariable String fileName) {
        logger.info("Received request to get file: {}", fileName);
        try {
            if (!fileService.fileExists(fileName)) {
                return ResponseEntity.notFound().build();
            }

            InputStream fileStream = fileService.getFile(fileName);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentDispositionFormData("attachment", fileName);
            
            // Try to determine content type, default to octet-stream
            String contentType = determineContentType(fileName);
            headers.setContentType(MediaType.parseMediaType(contentType));

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(new InputStreamResource(fileStream));
        } catch (Exception e) {
            logger.error("Error getting file: {}", fileName, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<String>> listFiles() {
        logger.info("Received request to list all files");
        try {
            List<String> files = fileService.listFiles();
            return ResponseEntity.ok(files);
        } catch (Exception e) {
            logger.error("Error listing files", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private String determineContentType(String fileName) {
        String extension = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
        switch (extension) {
            case "jpg":
            case "jpeg":
                return "image/jpeg";
            case "png":
                return "image/png";
            case "gif":
                return "image/gif";
            case "pdf":
                return "application/pdf";
            case "txt":
                return "text/plain";
            case "html":
                return "text/html";
            case "css":
                return "text/css";
            case "js":
                return "application/javascript";
            case "json":
                return "application/json";
            case "xml":
                return "application/xml";
            default:
                return "application/octet-stream";
        }
    }
}