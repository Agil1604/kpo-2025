package kpo.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import kpo.enums.OrderStatus;

public record GetOrderStatusResponse(
        @Pattern(regexp = "NEW|COMPLETE|CANCELLED", message = "Допустимые значения: NEW, COMPLETE, CANCELLED")
        @Schema(description = "Статус заказа (NEW, COMPLETE, CANCELLED)", example = "NEW")
        OrderStatus status
) {}