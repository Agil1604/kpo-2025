package hse.zoo.zoo.interfaces;

import hse.zoo.zoo.property.abstract_classes.Worker;

public interface IWorkerService {
    /**
     * Подсчитывает количество работников в данном зоопарке
     * @return число животных
     */
    int getCount();

    /**
     * Печатает информацию о всех хранимых особях
     */
    void printInfo();

    /**
     * Добавляет работника в учет
     * @param worker Добавляемый работник
     */
    void add(Worker worker);

    /**
     * Подсчет общего объема пищи, потребляемого работниками данного зоопарка
     * @return Объем пищи в кг
     */
    int totalFood();
}
