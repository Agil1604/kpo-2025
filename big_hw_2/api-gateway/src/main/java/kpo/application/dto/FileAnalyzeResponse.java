package kpo.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record FileAnalyzeResponse (
        @Schema(description = "Количество параграфов в файле")
        int paragraphs,

        @Schema(description = "Количество слов в файле")
        int words,

        @Schema(description = "Количество символов в файле")
        int symbols,

        @Schema(description = "Процент плагиата")
        double plagiarism
) {}
