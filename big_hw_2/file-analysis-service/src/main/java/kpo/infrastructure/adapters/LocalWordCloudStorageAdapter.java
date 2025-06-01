package kpo.infrastructure.adapters;

import kpo.domain.port.WordCloudStoragePort;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Component
public class LocalWordCloudStorageAdapter implements WordCloudStoragePort {
    private final Path storageRoot = Paths.get("wordclouds").toAbsolutePath().normalize();

    @Override
    public String storeWordCloud(byte[] imageData, String fileExtension) throws IOException {
        String filename = "wordcloud_" + UUID.randomUUID() +
                (fileExtension.startsWith(".") ? fileExtension : "." + fileExtension);

        Path filePath = storageRoot.resolve(filename);
        Files.createDirectories(storageRoot);
        Files.write(filePath, imageData);
        return filePath.toString();
    }

    @Override
    public byte[] getWordCloud(String filePath) throws IOException {
        return Files.readAllBytes(Path.of(filePath));
    }
}