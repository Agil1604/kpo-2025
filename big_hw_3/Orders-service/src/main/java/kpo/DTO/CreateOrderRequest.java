package kpo.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;

public record CreateOrderRequest(
        @Positive
        @Schema(description = "id пользователя, который оформил заказ", example = "1")
        Integer userId,

        @Positive
        @Schema(description = "Сумма заказа (должна быть не меньше баланса на счете пользователя)", example = "1984")
        Integer amount,

        @Schema(description = "Описание заказа", example = "Шоппинг")
        String description
) {}