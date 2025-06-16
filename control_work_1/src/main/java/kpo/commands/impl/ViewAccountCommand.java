package kpo.commands.impl;

import kpo.commands.Command;
import kpo.facade.FinanceFacade;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ViewAccountCommand implements Command  {
    private final FinanceFacade facade;
    private final int id;

    @Override
    public void execute() {
        var account = facade.getBankAccount(id);
        System.out.println("Счет: " + account);
    }
}
