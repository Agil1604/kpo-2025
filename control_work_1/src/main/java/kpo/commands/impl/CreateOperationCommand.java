package kpo.commands.impl;

import kpo.commands.Command;

import kpo.enums.OperationType;
import kpo.facade.FinanceFacade;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateOperationCommand implements Command {
    private final FinanceFacade facade;
    private final int accountId;
    private final int categoryId;
    private final OperationType type;
    private final int amount;
    private final String description;

    @Override
    public void execute() {
        var operation = facade.createOperation(accountId, categoryId, type, amount, description);
        System.out.println("Операция создана: ID=" + operation.operationId());
    }
}