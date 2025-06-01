package kpo.infrastructure.persistence;

import kpo.infrastructure.entities.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileJpaRepository extends JpaRepository<FileEntity, Integer> {
    Optional<FileEntity> findByFileHash(String fileHash);
}