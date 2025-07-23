package kpo.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import kpo.DTO.CreateOrderRequest;
import kpo.DTO.CreateOrderResponse;
import kpo.DTO.GetOrderStatusResponse;
import kpo.DTO.GetOrdersResponse;
import kpo.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/order")
@Tag(
        name = "Точки входа для работы над заказами",
        description = "Все запросы с началом /api/order отвечают за работу с заказами"
)
public class OperationServiceController {
    private final OrderService orderService;

    @GetMapping("/{userId}")
    @Operation(summary = "Получение списка всех заказов пользователя по userId")
    public ResponseEntity<GetOrdersResponse> getUserOrders(@PathVariable @Min(1) int userId) {
        return ResponseEntity.ok().body(new GetOrdersResponse(orderService.getOrdersByUserId(userId)));
    }

    @GetMapping("/status/{orderId}")
    @Operation(summary = "Получение статуса заказа пользователя по orderId")
    public ResponseEntity<GetOrderStatusResponse> getOrderStatus(@PathVariable @Min(1) int orderId) {
        return ResponseEntity.ok(new GetOrderStatusResponse(orderService.getOrder(orderId).getStatus()));
    }

    @PostMapping("/create")
    @Operation(summary = "Создание заказа")
    public ResponseEntity<CreateOrderResponse> createOrder(
            @Validated @RequestBody CreateOrderRequest request
    ) {
        return ResponseEntity.ok().body(new CreateOrderResponse(orderService.createOrder(request.userId(), request.amount(), request.description())));
    }
}
