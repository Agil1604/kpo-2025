package kpo.commands.impl;

import kpo.commands.Command;
import kpo.facade.FinanceFacade;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateAccountNameCommand implements Command {
    private final FinanceFacade facade;
    private final int id;
    private final String name;

    @Override
    public void execute() {
        var account = facade.updateBankAccountName(id, name);
        System.out.println("Название изменено: " + account.getName());
    }
}