package kpo.factories;

import kpo.domains.BankAccount;
import kpo.domains.Category;
import kpo.domains.Operation;
import kpo.enums.CategoryType;
import kpo.enums.OperationType;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Абстрактная фабрика для создания доменных объектов.
 */
public interface DomainFactory {

    BankAccount createAccount(String name, Integer balance);

    Category createCategory(CategoryType type, String name);

    Operation createOperation(Integer accountId, Integer categoryId,
                              OperationType type, Integer amount, LocalDateTime date, String description);

    default Integer generateId() {
        return ThreadLocalRandom.current().nextInt(1, Integer.MAX_VALUE);
    }
}