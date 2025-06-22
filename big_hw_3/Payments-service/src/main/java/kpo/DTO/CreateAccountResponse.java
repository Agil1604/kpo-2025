package kpo.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import kpo.entities.BankAccountEntity;

public record CreateAccountResponse (
    @Schema(description = "Экземпляр сущности созданного банковского аккаунта")
    BankAccountEntity bankAccount
) {}
