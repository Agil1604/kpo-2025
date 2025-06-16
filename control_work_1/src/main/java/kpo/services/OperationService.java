package kpo.services;

import kpo.domains.BankAccount;
import kpo.domains.Operation;
import kpo.enums.OperationType;
import kpo.exceptions.EntityNotFoundException;
import kpo.factories.DomainFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OperationService {
    private final Map<Integer, Operation> operations = new HashMap<>();
    private final DomainFactory factory;
    private final BankAccountService accountService;

    public Operation createOperation(Integer accountId, Integer categoryId,
                                     OperationType type, Integer amount,
                                     LocalDateTime date, String description) {
        BankAccount account = accountService.getAccount(accountId);

        Operation operation = factory.createOperation(
                accountId, categoryId, type, amount, date, description
        );

        if (type == OperationType.INCOME) {
            account.deposit(amount);
        } else {
            account.withdraw(amount);
        }

        operations.put(operation.operationId(), operation);
        return operation;
    }

    public Operation getOperation(Integer id) {
        return Optional.ofNullable(operations.get(id))
                .orElseThrow(() -> new EntityNotFoundException("Operation not found"));
    }

}