package kpo.application.usecases;

import kpo.domain.exception.FileNotFoundException;
import kpo.domain.exception.ServiceUnavailableException;
import kpo.domain.model.AnalyzeResult;
import kpo.domain.model.FileContent;
import kpo.domain.port.AnalyzeRepositoryPort;
import kpo.domain.port.FileStoragePort;
import kpo.domain.port.WordCloudStoragePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Logger;

@Component
@RequiredArgsConstructor
public class AnalyzeFileUseCase {
    private final RestTemplate restTemplate;
    private final AnalyzeRepositoryPort analyzeRepository;
    private final WordCloudStoragePort wordCloudStorage;
    private final FileStoragePort fileStorage;

    private static final Logger logger = Logger.getLogger(AnalyzeFileUseCase.class.getName());

    public AnalyzeResult execute(Integer fileId) throws IOException, FileNotFoundException, ServiceUnavailableException {
        Optional<AnalyzeResult> existingAnalyze = analyzeRepository.findByFileId(fileId);
        if (existingAnalyze.isPresent()) {
            return existingAnalyze.get();
        }

        FileContent fileContent = fileStorage.get(fileId);
        String content = fileContent.content();

        byte[] wordCloud = generateWordCloud(content);
        String wordCloudPath = null;

        if (wordCloud != null && wordCloud.length > 0) {
            try {
                wordCloudPath = wordCloudStorage.storeWordCloud(wordCloud, "png");
            } catch (IOException e) {
                logger.severe("Failed to store word cloud" + e);
            }
        }

        AnalyzeResult result = new AnalyzeResult(
                fileId,
                countParagraphs(content),
                countWords(content),
                countSymbols(content),
                0.6,  // Заглушка для plagiarism
                wordCloudPath
        );

        AnalyzeResult savedResult = analyzeRepository.save(result);

        if (savedResult.getAnalyzeId() == null) {
            throw new IllegalStateException("Analyze ID not generated");
        }

        return savedResult;
    }

    private Integer countSymbols(String content) {
        return content.length();
    }

    private Integer countWords(String content) {
        if (content == null || content.trim().isEmpty()) {
            return 0;
        }
        Scanner scanner = new Scanner(content);
        int count = 0;
        while (scanner.hasNext()) {
            scanner.next();
            count++;
        }
        scanner.close();
        return count;
    }

    private Integer countParagraphs(String content) {
        if (content == null || content.trim().isEmpty()) {
            return 0;
        }
        String[] lines = content.split("\\R");
        int paragraphCount = 0;
        boolean inParagraph = false;

        for (String line : lines) {
            if (line.trim().isEmpty()) {
                inParagraph = false;
            } else if (!inParagraph) {
                inParagraph = true;
                paragraphCount++;
            }
        }
        return paragraphCount;
    }

    public byte[] generateWordCloud(String content) {
        String encodedText = URLEncoder.encode(content, StandardCharsets.UTF_8);
        logger.info(encodedText);
        String url = String.format(
                "https://quickchart.io/wordcloud?text=%s&format=png&width=800&height=600",
                encodedText
        );

        ResponseEntity<byte[]> response = restTemplate.getForEntity(url, byte[].class);
        return response.getBody();
    }
}
