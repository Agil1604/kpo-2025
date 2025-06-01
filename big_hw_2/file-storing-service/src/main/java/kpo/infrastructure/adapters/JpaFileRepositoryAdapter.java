package kpo.infrastructure.adapters;

import kpo.domain.ports.FileRepositoryPort;
import kpo.domain.model.File;
import kpo.infrastructure.entities.FileEntity;
import kpo.infrastructure.persistence.FileJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JpaFileRepositoryAdapter implements FileRepositoryPort {
    private final FileJpaRepository jpaRepository;

    @Override
    public Optional<File> findById(Integer id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public File save(File file) {
        FileEntity entity = toEntity(file);
        FileEntity savedEntity = jpaRepository.save(entity);
        return toDomain(savedEntity);
    }

    @Override
    public Optional<File> findByFileHash(String fileHash) {
        return jpaRepository.findByFileHash(fileHash).map(this::toDomain);
    }

    private File toDomain(FileEntity entity) {
        File domain = new File(
                entity.getFileName(),
                entity.getFilePath(),
                entity.getFileHash()
        );
        domain.setId(entity.getId());
        return domain;
    }

    private FileEntity toEntity(File domain) {
        FileEntity entity = new FileEntity(
                domain.getFileName(),
                domain.getFilePath(),
                domain.getFileHash()
        );
        if (domain.getId() != null) {
            entity.setId(domain.getId());
        }
        return entity;
    }
}
