package kpo.DTO;

import io.swagger.v3.oas.annotations.media.Schema;

public record ReplenishAccountResponse (
    @Schema(description = "Итоговый баланс счета")
    Integer balance
){}
