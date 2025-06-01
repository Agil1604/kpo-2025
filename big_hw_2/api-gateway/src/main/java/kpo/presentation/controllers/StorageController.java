package kpo.presentation.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import kpo.application.services.FileStorageUseCase;
import kpo.domain.exception.InvalidFileTypeException;
import kpo.application.dto.FileGetResponse;
import kpo.application.dto.FileUploadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/storage")
@Tag(
        name = "Точки входа для сервиса хранения файлов",
        description = "Все полученные запросы с началом /api/storage перенаправляются в file-storing-service"
)
public class StorageController {
    private final FileStorageUseCase fileStorageUseCase;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Загружает файл на сервер")
    public ResponseEntity<FileUploadResponse> uploadFile(@RequestPart("file") MultipartFile file) {
        try {
            int fileId = fileStorageUseCase.uploadFile(file);
            return ResponseEntity.ok().body(new FileUploadResponse(fileId));
        } catch (InvalidFileTypeException e) {
            return ResponseEntity.badRequest().build();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получает содержимое файла по id")
    public ResponseEntity<FileGetResponse> getFile(@PathVariable @Min(1) int id) {
        var file = fileStorageUseCase.getFile(id);
        return ResponseEntity.ok().body(
                new FileGetResponse(file.filename(), file.content())
        );
    }
}