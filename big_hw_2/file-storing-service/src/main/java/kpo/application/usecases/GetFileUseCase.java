package kpo.application.usecases;

import kpo.domain.ports.FileRepositoryPort;
import kpo.domain.ports.FileStoragePort;
import kpo.domain.model.File;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class GetFileUseCase {
    private final FileRepositoryPort fileRepository;
    private final FileStoragePort fileStorage;

    public FileContent execute(Integer fileId) throws IOException {
        File file = fileRepository.findById(fileId)
                .orElseThrow(() -> new RuntimeException("File not found"));
        String content = fileStorage.readFileContent(file.getFilePath());
        return new FileContent(file.getFileName(), content);
    }

    public record FileContent(String filename, String content) {}
}