package kpo.factories.impl;

import kpo.domains.BankAccount;
import kpo.domains.Category;
import kpo.domains.Operation;
import kpo.enums.CategoryType;
import kpo.enums.OperationType;
import kpo.factories.DomainFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Конкретная реализация фабрики доменных объектов
 */
@Component
public class DomainFactoryImpl implements DomainFactory {

    @Override
    public BankAccount createAccount(String name, Integer balance) {
        Integer id = generateId();
        if (balance < 0) throw new IllegalArgumentException("Баланс не может быть отрицательным");
        return new BankAccount(
                id,
                name,
                balance
        );
    }

    @Override
    public Category createCategory(CategoryType type, String name) {
        Integer id = generateId();
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Название категории обязательно");
        return new Category(
                id,
                type,
                name
        );
    }

    @Override
    public Operation createOperation(Integer accountId, Integer categoryId,
                                     OperationType type, Integer amount, LocalDateTime date, String description) {
        Integer operationId = generateId();

        if (amount <= 0) throw new IllegalArgumentException("Сумма операции должна быть положительной");
        return new Operation(
                operationId,
                accountId,
                categoryId,
                type,
                amount,
                date,
                description
        );
    }
}
