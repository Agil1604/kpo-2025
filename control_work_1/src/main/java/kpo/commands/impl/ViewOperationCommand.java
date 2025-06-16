package kpo.commands.impl;

import kpo.commands.Command;
import kpo.facade.FinanceFacade;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ViewOperationCommand implements Command {
    private final FinanceFacade facade;
    private final int id;

    @Override
    public void execute() {
        var category = facade.getCategory(id);
        System.out.println("Категория: " + category);
    }
}
