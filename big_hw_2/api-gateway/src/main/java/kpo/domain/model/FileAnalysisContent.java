package kpo.domain.model;

public record FileAnalysisContent(
        int analysis_id,
        int file_id,
        int paragraphs,
        int words,
        int symbols,
        double plagiarism
) {}
