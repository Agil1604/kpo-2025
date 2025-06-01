package kpo.application.services;

import kpo.domain.exception.InvalidFileTypeException;
import kpo.domain.model.FileContent;
import kpo.domain.port.FileStoragePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class FileStorageUseCase {

    private final FileStoragePort storagePort;

    public int uploadFile(MultipartFile file) throws IOException {
        validateFileExtension(file.getOriginalFilename());
        String content = new String(file.getBytes(), StandardCharsets.UTF_8);
        return storagePort.upload(file.getOriginalFilename(), content);
    }

    public int uploadFile(String filename, String content) {
        validateFileExtension(filename);
        return storagePort.upload(filename, content);
    }

    public FileContent getFile(int fileId) {
        return storagePort.get(fileId);
    }

    private void validateFileExtension(String filename) {
        if (filename == null || !filename.toLowerCase().endsWith(".txt")) {
            throw new InvalidFileTypeException("Only .txt files allowed");
        }
    }
}