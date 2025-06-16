package kpo.facade;

import kpo.domains.BankAccount;
import kpo.domains.Category;
import kpo.domains.Operation;
import kpo.enums.CategoryType;
import kpo.enums.OperationType;
import kpo.services.BankAccountService;
import kpo.services.CategoryService;
import kpo.services.OperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class FinanceFacade {
    private final BankAccountService accountService;
    private final CategoryService categoryService;
    private final OperationService operationService;

    // Create операции

    public BankAccount createAccount(String name, Integer balance) {
        return accountService.createAccount(name, balance);
    }

    public Operation createOperation(Integer accountId, Integer categoryId,
                                     OperationType type, Integer amount,
                                     String description) {
        return operationService.createOperation(
                accountId, categoryId, type, amount, LocalDateTime.now(), description
        );
    }

    public Category createCategory(CategoryType type, String name) {
        return categoryService.createCategory(type, name);
    }

    // Update операции

    public BankAccount updateBankAccountName(Integer id, String name) {
        return accountService.updateAccountName(id, name);
    }

    // Delete операции

    public BankAccount deleteBankAccount(Integer id) {
        return accountService.deleteAccount(id);
    }

    public Category deleteCategory(Integer id) {
        return categoryService.deleteCategory(id);
    }

    // get операции

    public BankAccount getBankAccount(Integer id) {
        return accountService.getAccount(id);
    }

    public Operation getOperation(Integer id) {
        return operationService.getOperation(id);
    }

    public Category getCategory(Integer id) {
        return categoryService.getCategory(id);
    }
    // Аналитика
//    public int getBalanceDifference(int accountId, LocalDate start, LocalDate end) {
//        return analyticsService.calculateBalanceDifference(accountId, start, end);
//    }

//    public Map<Category, Integer> getCategorySummary(int accountId,
//                                                     LocalDate start,
//                                                     LocalDate end) {
//        return analyticsService.groupByCategories(accountId, start, end);
//    }
}