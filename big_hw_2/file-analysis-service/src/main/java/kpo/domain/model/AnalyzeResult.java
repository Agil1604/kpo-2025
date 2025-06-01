package kpo.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AnalyzeResult {
    private Integer analyzeId;
    private Integer fileId;
    private Integer paragraphs;
    private Integer words;
    private Integer symbols;
    private Double plagiarism;
    private String wordCloudPath;

    public AnalyzeResult(Integer fileId, Integer paragraphs, Integer words, Integer symbols, Double plagiarism, String wordCloudPath) {
        this.fileId = fileId;
        this.paragraphs = paragraphs;
        this.words = words;
        this.symbols = symbols;
        this.plagiarism = plagiarism;
        this.wordCloudPath = wordCloudPath;
    }
}