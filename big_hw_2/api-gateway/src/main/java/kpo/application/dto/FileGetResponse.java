package kpo.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record FileGetResponse(
        @Schema(description = "Имя файла", example = "document.txt")
        String filename,

        @Schema(description = "Содержимое файла", example = "Hello world")
        String content
) {}