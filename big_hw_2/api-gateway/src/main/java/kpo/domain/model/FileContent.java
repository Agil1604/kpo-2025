package kpo.domain.model;

public record FileContent(
        int id,
        String filename,
        String content
) {}