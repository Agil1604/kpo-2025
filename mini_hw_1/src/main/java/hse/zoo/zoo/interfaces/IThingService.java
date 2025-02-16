package hse.zoo.zoo.interfaces;

import hse.zoo.zoo.property.abstract_classes.Thing;

public interface IThingService {

    /**
     * Подсчитывает количество объектов в данном зоопарке
     * @return число объектов
     */
    int getCount();

    /**
     * Печатает информацию о всех хранимых объектах
     */
    void printInfo();

    /**
     * Добавляет предмет в учет
     * @param thing Добавляемый предмет
     */
    void add(Thing thing);
}
