package hse.zoo.zoo.interfaces;

import hse.zoo.zoo.property.abstract_classes.Animals.Animal;

public interface IAnimalService {
    /**
     * Подсчитывает количество животных в данном зоопарке
     * @return число животных
     */
    int getCount();

    /**
     * Печатает информацию о всех хранимых особях
     */
    void printInfo();

    /**
     * Добавляет особь в учет
     * @param animal добавляемая особь
     */
    void add(Animal animal);

    /**
     * Подсчет общего объема пищи, потребляемого животными данного зоопарка
     * @return Объем пищи в кг
     */
    int totalFood();

    /**
     * Печатает список животных, которых можно использовать в контактном зоопарке
     */
    void getPlayables();
}
