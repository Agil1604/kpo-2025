package kpo.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;

public record WithdrawResponse (
        @Positive
        @Schema(description = "Текущий баланс на счете")
        Integer balance
){}
