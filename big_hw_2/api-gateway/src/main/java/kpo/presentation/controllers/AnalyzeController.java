package kpo.presentation.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import kpo.application.dto.FileAnalyzeResponse;
import kpo.application.services.FileAnalysisUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/analyze")
@Tag(
        name = "Точки входа для сервиса анализа файлов",
        description = "Все полученные запросы с началом /api/analyze перенаправляются в file-analyze-service"
)
public class AnalyzeController {
    private final FileAnalysisUseCase fileAnalysisUseCase;

    @PostMapping("/{id}")
    @Operation(summary = "Анализирует содержимое файла на плагиат по id")
    public ResponseEntity<FileAnalyzeResponse> analyzeFile(@PathVariable @Min(1) int id) {
        var result = fileAnalysisUseCase.analyzeFile(id);
        return ResponseEntity.ok().body(
                new FileAnalyzeResponse(result.paragraphs(), result.words(), result.symbols(), result.plagiarism())
        );
    }

    @GetMapping("/cloud/{id}")
    @Operation(summary = "Получает облако слов для файла по id")
    public ResponseEntity<byte[]> getCloud(@PathVariable @Min(1) int id) {
        byte[] cloudImage = fileAnalysisUseCase.getCloud(id);

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"wordcloud_" + id + ".png\"")
                .body(cloudImage);
    }
}
