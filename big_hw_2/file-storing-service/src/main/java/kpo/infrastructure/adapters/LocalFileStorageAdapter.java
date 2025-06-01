package kpo.infrastructure.adapters;

import kpo.domain.ports.FileStoragePort;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Component
public class LocalFileStorageAdapter implements FileStoragePort {
    private final Path uploadRoot = Paths.get("uploads").toAbsolutePath().normalize();

    @Override
    public String storeFile(String filename, String content) throws IOException {
        String uniqueName = UUID.randomUUID() + "_" + filename;
        Path filePath = uploadRoot.resolve(uniqueName);
        Files.createDirectories(uploadRoot);
        Files.writeString(filePath, content);
        return filePath.toString();
    }

    @Override
    public String readFileContent(String filePath) throws IOException {
        return Files.readString(Path.of(filePath));
    }
}