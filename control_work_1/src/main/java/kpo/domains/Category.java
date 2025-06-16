package kpo.domains;

import kpo.enums.CategoryType;

/**
 * Доменный класс, представляющий категорию.
 */
public record Category(Integer id, CategoryType type, String name) {}