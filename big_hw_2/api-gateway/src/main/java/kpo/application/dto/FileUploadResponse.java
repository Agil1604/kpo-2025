package kpo.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.Builder;

@Builder
public record FileUploadResponse(
        @Schema(description = "id запрашиваемого файла", example = "1")
        @Min(1)
        int fileId
) {}