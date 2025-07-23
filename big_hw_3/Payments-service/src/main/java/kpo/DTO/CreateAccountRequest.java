package kpo.DTO;

import io.swagger.v3.oas.annotations.media.Schema;

public record CreateAccountRequest (
        @Schema(description = "Первоначальный баланс счета")
        Integer balance
){}
