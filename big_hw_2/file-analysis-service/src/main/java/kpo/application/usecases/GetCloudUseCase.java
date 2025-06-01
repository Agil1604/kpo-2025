package kpo.application.usecases;

import kpo.domain.exception.CloudNotFoundException;
import kpo.domain.exception.FileNotFoundException;
import kpo.domain.exception.ServiceUnavailableException;
import kpo.domain.model.AnalyzeResult;
import kpo.domain.port.AnalyzeRepositoryPort;
import kpo.domain.port.WordCloudStoragePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GetCloudUseCase {
    private final WordCloudStoragePort wordCloudStorage;
    private final AnalyzeRepositoryPort analyzeRepository;

    public byte[] execute(Integer fileId) throws IOException, FileNotFoundException, ServiceUnavailableException, CloudNotFoundException {
        Optional<AnalyzeResult> resultOpt = analyzeRepository.findByFileId(fileId);
        if (resultOpt.isEmpty()) {
            throw new CloudNotFoundException("Analysis not found for file: " + fileId);
        }

        AnalyzeResult result = resultOpt.get();
        if (result.getWordCloudPath() == null) {
            throw new CloudNotFoundException("Word cloud not generated for file: " + fileId);
        }

        return wordCloudStorage.getWordCloud(result.getWordCloudPath());
    }

}