package com.example.saiyanjinsports.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileService {
    private static final String uploadDir = "src/main/resources/static/css/images";
    public static void saveFile(String fileName, MultipartFile file) throws IOException {
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        try (InputStream inputStream = file.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new IOException("Could not save file: " + fileName, e);
        }
    }

    public byte[] getFile(String fileName) throws IOException {
        Path path = Paths.get(String.format("%s/%s",uploadDir,fileName));
        byte[] imageBytes = Files.readAllBytes(path);
        return imageBytes;
    }

    public void deleteFile(String fileName) {
        Path path = Paths.get(String.format("%s/%s",uploadDir,fileName));
        File file = new File(path.toString());
        if (file.exists()) {
            file.delete();
        }
    }

}
