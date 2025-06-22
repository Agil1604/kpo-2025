package kpo.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record GetBalanceResponse(
        @Schema(description = "Баланс счета", example = "3000")
        @NotNull(message = "Сумма обязательна")
        @Positive(message = "Сумма должна быть положительной")
        Integer sum
) {}
