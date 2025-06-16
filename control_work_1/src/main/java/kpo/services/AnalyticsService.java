package kpo.services;

import kpo.domains.Category;
import kpo.domains.Operation;
import kpo.enums.OperationType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
class AnalyticsService {
    private final OperationService operationService;
    private final CategoryService categoryService;

//    public int calculateBalanceDifference(int accountId, LocalDate start, LocalDate end) {
//        List<Operation> operations = operationService.getOperations(accountId, start, end);
//
//        int income = operations.stream()
//                .filter(op -> op.type() == OperationType.INCOME)
//                .mapToInt(Operation::amount)
//                .sum();
//
//        int expenses = operations.stream()
//                .filter(op -> op.type() == OperationType.EXPENSE)
//                .mapToInt(Operation::amount)
//                .sum();
//
//        return income - expenses;
//    }

//    public Map<Category, Integer> groupByCategories(int accountId,
//                                                    LocalDate start,
//                                                    LocalDate end) {
//        return operationService.getOperations(accountId, start, end).stream()
//                .collect(Collectors.groupingBy(
//                        op -> categoryService.getCategory(op.categoryId()),
//                        Collectors.summingInt(Operation::amount)
//                ));
//    }
}