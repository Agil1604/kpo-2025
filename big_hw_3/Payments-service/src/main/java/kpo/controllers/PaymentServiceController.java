package kpo.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import kpo.DTO.*;
import kpo.services.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/payment")
@Tag(
        name = "Точки входа для работы над пользовательским счетом",
        description = "Все полученные запросы с началом /api/payment перенаправляются в Payment-service"
)
public class PaymentServiceController {
    private final PaymentService paymentService;

    @PostMapping("/create/{userId}")
    @Operation(summary = "Создает счет для нового пользователя по userId")
    public ResponseEntity<CreateAccountResponse> createAccount(
            @PathVariable @Min(1) int userId,
            @Validated @RequestBody CreateAccountRequest request
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new CreateAccountResponse(paymentService.createAccount(userId, request.balance())));
    }

    @PatchMapping("/replenish/{userId}")
    @Operation(summary = "Пополнение пользовательского счета на sum единиц")
    public ResponseEntity<ReplenishAccountResponse> replenishAccount(
            @PathVariable @Min(1) int userId,
                @Validated @RequestBody ReplenishAccountRequest request
    ) {
        return ResponseEntity.ok().body(
                new ReplenishAccountResponse(
                        paymentService.replenishAccount(
                                userId,
                                request.sum()
                        ).getBalance()
                )
        );
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Получение суммы средств на счете")
    public ResponseEntity<GetBalanceResponse> getBalance(@PathVariable @Min(1) int userId) {
        return ResponseEntity.ok().body(
                new GetBalanceResponse(
                        paymentService.getAccountByUserId(userId).getBalance()
                )
        );
    }

    @PatchMapping("/withdraw/{userId}")
    @Operation(summary = "Снятие средств со счета пользователя")
    public ResponseEntity<WithdrawResponse> withdraw(
            @PathVariable @Min(1) int userId,
            @Validated @RequestBody WithdrawRequest request
    ) {
        return ResponseEntity.ok().body(
                new WithdrawResponse(
                        paymentService.withdraw(
                                userId,
                                request.sum()
                        ).getBalance()
                )
        );
    }

}