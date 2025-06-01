package kpo.infrastructure.repositories;

import kpo.infrastructure.entities.AnalyzeResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnalyzeJpaRepository extends JpaRepository<AnalyzeResultEntity, Integer> {
    Optional<AnalyzeResultEntity> findByFileId(Integer fileId);
}
