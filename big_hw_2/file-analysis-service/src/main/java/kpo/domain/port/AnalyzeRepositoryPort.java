package kpo.domain.port;

import kpo.domain.model.AnalyzeResult;

import java.util.Optional;

public interface AnalyzeRepositoryPort {
    AnalyzeResult save(AnalyzeResult file);
    Optional<AnalyzeResult> findById(Integer id);
    Optional<AnalyzeResult> findByFileId(Integer fileId);
}
