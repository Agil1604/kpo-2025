package kpo.domain.ports;

import kpo.domain.model.File;
import java.util.Optional;

public interface FileRepositoryPort {
    Optional<File> findById(Integer id);
    File save(File file);
    Optional<File> findByFileHash(String fileHash);
}