package kpo.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import kpo.entities.OrderEntity;
import java.util.List;

public record GetOrdersResponse(
        @Schema(description = "Список всех заказов пользователя")
        List<OrderEntity> orders
) {}