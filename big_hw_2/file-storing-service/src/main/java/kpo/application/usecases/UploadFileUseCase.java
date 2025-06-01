package kpo.application.usecases;

import kpo.domain.ports.FileRepositoryPort;
import kpo.domain.ports.FileStoragePort;
import kpo.domain.model.File;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UploadFileUseCase {
    private final FileRepositoryPort fileRepository;
    private final FileStoragePort fileStorage;

    public File execute(String filename, String content) throws IOException, NoSuchAlgorithmException {
        String hash = calculateSHA256(content);
        Optional<File> existingFile = fileRepository.findByFileHash(hash);
        if (existingFile.isPresent()) {
            return existingFile.get();
        }

        String filePath = fileStorage.storeFile(filename, content);
        File file = new File(filename, filePath, hash);
        File savedFile = fileRepository.save(file);

        if (savedFile.getId() == null) {
            throw new IllegalStateException("File ID not generated after save");
        }

        return savedFile;
    }

    private String calculateSHA256(String content) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = digest.digest(content.getBytes(StandardCharsets.UTF_8));

        BigInteger number = new BigInteger(1, hashBytes);
        StringBuilder hexString = new StringBuilder(number.toString(16));

        while (hexString.length() < 64) {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }
}
