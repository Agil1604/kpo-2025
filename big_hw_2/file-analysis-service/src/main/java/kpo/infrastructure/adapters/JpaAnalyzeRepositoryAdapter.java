package kpo.infrastructure.adapters;

import kpo.domain.model.AnalyzeResult;
import kpo.infrastructure.entities.AnalyzeResultEntity;
import kpo.domain.port.AnalyzeRepositoryPort;
import kpo.infrastructure.repositories.AnalyzeJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JpaAnalyzeRepositoryAdapter implements AnalyzeRepositoryPort {
    private final AnalyzeJpaRepository jpaRepository;

    @Override
    public Optional<AnalyzeResult> findById(Integer id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public Optional<AnalyzeResult> findByFileId(Integer fileId) {
        return jpaRepository.findByFileId(fileId).map(this::toDomain);
    }

    @Override
    public AnalyzeResult save(AnalyzeResult file) {
        AnalyzeResultEntity entity = toEntity(file);
        AnalyzeResultEntity savedEntity = jpaRepository.save(entity);
        return toDomain(savedEntity);
    }

    private AnalyzeResult toDomain(AnalyzeResultEntity entity) {
        AnalyzeResult domain = new AnalyzeResult(
                entity.getFileId(),
                entity.getParagraphs(),
                entity.getWords(),
                entity.getSymbols(),
                entity.getPlagiarism(),
                entity.getWordCloudPath()
        );
        domain.setAnalyzeId(entity.getId());
        return domain;
    }

    private AnalyzeResultEntity toEntity(AnalyzeResult domain) {
        AnalyzeResultEntity entity = new AnalyzeResultEntity(
                domain.getFileId(),
                domain.getParagraphs(),
                domain.getWords(),
                domain.getSymbols(),
                domain.getPlagiarism(),
                domain.getWordCloudPath()
        );
        if (domain.getAnalyzeId() != null) {
            entity.setId(domain.getAnalyzeId());
        }
        return entity;
    }
}
