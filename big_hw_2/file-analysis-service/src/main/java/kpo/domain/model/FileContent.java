package kpo.domain.model;

public record FileContent(
        int fileId,
        String filename,
        String content
) {}