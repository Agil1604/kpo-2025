package kpo.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;

public record WithdrawRequest (
        @Positive
        @Schema(description = "Сумма снятия со счета")
        Integer sum
){}
