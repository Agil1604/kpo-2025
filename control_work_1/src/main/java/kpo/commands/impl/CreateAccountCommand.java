package kpo.commands.impl;

import kpo.commands.Command;
import kpo.facade.FinanceFacade;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateAccountCommand implements Command {
    private final FinanceFacade facade;
    private final String name;
    private final int balance;

    @Override
    public void execute() {
        var account = facade.createAccount(name, balance);
        System.out.println("Счет создан: ID=" + account.getId());
    }
}