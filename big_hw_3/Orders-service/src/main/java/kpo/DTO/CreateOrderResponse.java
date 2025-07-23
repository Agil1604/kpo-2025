package kpo.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import kpo.entities.OrderEntity;

public record CreateOrderResponse (
        @Schema(description = "Содержимое созданного заказа")
        OrderEntity order
) {}
