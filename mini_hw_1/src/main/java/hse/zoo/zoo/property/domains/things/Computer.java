package hse.zoo.zoo.property.domains.things;

import hse.zoo.zoo.property.abstract_classes.Thing;

/**
 * Класс для компьютеров
 */
public class Computer extends Thing {
    /**
     * Конструктор компьютера
     * @param Name Имя
     * @param Number идентификационный номер
     */
    public Computer(String Name, int Number){
        this.setName(Name);
        this.setNumber(Number);
    }

    @Override
    public String toString(){
        return String.format("Computer %s: inventory number = %d", this.getName(), this.getNumber());
    }
}
