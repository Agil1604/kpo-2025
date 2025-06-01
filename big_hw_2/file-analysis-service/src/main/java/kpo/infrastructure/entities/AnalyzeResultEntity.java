package kpo.infrastructure.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "analyze_result")
@NoArgsConstructor
public class AnalyzeResultEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer fileId;

    private Integer paragraphs;
    private Integer words;
    private Integer symbols;
    private Double plagiarism;
    private String wordCloudPath;

    public AnalyzeResultEntity(Integer fileId, Integer paragraphs, Integer words, Integer symbols, Double plagiarism, String wordCloudPath) {
        this.fileId = fileId;
        this.paragraphs = paragraphs;
        this.words = words;
        this.symbols = symbols;
        this.plagiarism = plagiarism;
        this.wordCloudPath = wordCloudPath;
    }
}