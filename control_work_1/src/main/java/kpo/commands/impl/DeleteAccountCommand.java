package kpo.commands.impl;

import kpo.commands.Command;
import kpo.facade.FinanceFacade;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteAccountCommand implements Command  {
    private final FinanceFacade facade;
    private final int id;

    @Override
    public void execute() {
        var account = facade.deleteBankAccount(id);
        System.out.println("Счет удален: " + account.getName());
    }
}
