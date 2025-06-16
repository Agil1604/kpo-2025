package kpo.services;

import kpo.domains.BankAccount;
import kpo.domains.Category;
import kpo.enums.CategoryType;
import kpo.exceptions.EntityNotFoundException;
import kpo.factories.DomainFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final Map<Integer, Category> categories = new HashMap<>();
    private final DomainFactory factory;

    public Category createCategory(CategoryType type, String name) {
        Category category = factory.createCategory(type, name);
        categories.put(category.id(), category);
        return category;
    }

    public Category getCategory(Integer id) {
        return Optional.ofNullable(categories.get(id))
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));
    }

    public Category deleteCategory(Integer id) {
        Category removedCategory = categories.remove(id);
        if (removedCategory == null) {
            throw new EntityNotFoundException("Category not found");
        }
        return removedCategory;
    }
}
