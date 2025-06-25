package com.suiteonix.schoolpaz.file;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * Service for handling file operations.
 */
public interface FileService {

    /**
     * Upload a file to the storage.
     *
     * @param file     the file to upload
     * @param fileName the name to save the file as
     * @return the URL of the uploaded file
     */
    String uploadFile(MultipartFile file, String fileName);

    /**
     * Upload an image to the storage.
     *
     * @param image    the image file to upload
     * @param fileName the name to save the image as
     * @return the URL of the uploaded image
     */
    String uploadImage(MultipartFile image, String fileName);

    /**
     * Update an existing file in the storage.
     *
     * @param file     the new file to replace the existing one
     * @param fileName the name of the file to update
     * @return the URL of the updated file
     */
    String updateFile(MultipartFile file, String fileName);

    /**
     * Update an existing image in the storage.
     *
     * @param image    the new image to replace the existing one
     * @param fileName the name of the image to update
     * @return the URL of the updated image
     */
    String updateImage(MultipartFile image, String fileName);

    /**
     * Delete a file from the storage.
     *
     * @param fileName the name of the file to delete
     * @return true if the file was deleted successfully, false otherwise
     */
    boolean deleteFile(String fileName);

    /**
     * Get a file as an input stream.
     *
     * @param fileName the name of the file to get
     * @return an input stream of the file
     */
    InputStream getFile(String fileName);

    /**
     * Check if a file exists in the storage.
     *
     * @param fileName the name of the file to check
     * @return true if the file exists, false otherwise
     */
    boolean fileExists(String fileName);

    /**
     * List all files in the storage.
     *
     * @return a list of file names
     */
    List<String> listFiles();
}