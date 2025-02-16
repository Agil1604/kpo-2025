package hse.zoo.zoo.interfaces;

import hse.zoo.zoo.property.abstract_classes.Animals.Animal;

/**
 * Интерфейс для обязанностей всех клиник
 */
public interface IVetClinic {
    /**
     * Проверяет уровень здоровья животного
     * @param animal проверяемая особь
     * @return true, если животное здоровое, false, иначе
     */
    boolean is_healthy(Animal animal);
}
