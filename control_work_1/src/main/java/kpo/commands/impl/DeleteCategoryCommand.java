package kpo.commands.impl;

import kpo.commands.Command;
import kpo.facade.FinanceFacade;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteCategoryCommand implements Command  {
    private final FinanceFacade facade;
    private final int id;

    @Override
    public void execute() {
        var category = facade.deleteCategory(id);
        System.out.println("Категория удалена: " + category.name());
    }
}
