package kpo.commands.impl;

import kpo.commands.Command;
import kpo.enums.CategoryType;
import kpo.facade.FinanceFacade;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateCategoryCommand implements Command {
    private final FinanceFacade facade;
    private final CategoryType type;
    private final String name;

    @Override
    public void execute() {
        var category = facade.createCategory(type, name);
        System.out.println("Категория создана: ID=" + category.id());
    }
}