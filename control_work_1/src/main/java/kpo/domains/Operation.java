package kpo.domains;

import kpo.enums.OperationType;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

/**
 * Доменный класс, представляющий операцию.
 */
public record Operation(
        Integer operationId,
        Integer bankAccountId,
        Integer categoryId,
        OperationType type,
        @Positive Integer amount,
        LocalDateTime date,
        String description
) {}


