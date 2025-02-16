package hse.zoo.zoo.property.domains.predators;

import hse.zoo.zoo.property.abstract_classes.Animals.Predator;

/**
 * Класс для волков
 */
public class Wolf extends Predator {
    /**
     * Конструктор особи волка
     * @param Name Имя
     * @param Number идентификационный номер
     * @param Food объем потребляемой пищи (целое число кг)
     */
    public Wolf(String Name, int Number, int Food){
        this.setName(Name);
        this.setNumber(Number);
        this.setFood(Food);
    }
    @Override
    public String toString(){
        return String.format("Wolf %s: inventory number = %d, food consumption = %dkg", this.getName(), this.getNumber(), this.getFood());
    }
}
