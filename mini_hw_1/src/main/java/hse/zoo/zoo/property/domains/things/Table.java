package hse.zoo.zoo.property.domains.things;

import hse.zoo.zoo.property.abstract_classes.Thing;

/**
 * Класс для столов
 */
public class Table extends Thing {
    /**
     * Конструктор столов
     * @param Name Имя
     * @param Number идентификационный номер
     */
    public Table(String Name, int Number){
        this.setName(Name);
        this.setNumber(Number);
    }

    @Override
    public String toString(){
        return String.format("Table %s: inventory number = %d", this.getName(), this.getNumber());
    }
}
