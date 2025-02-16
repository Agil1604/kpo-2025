package hse.zoo.zoo.property.domains.predators;

import hse.zoo.zoo.property.abstract_classes.Animals.Predator;

/**
 * Класс для тигров
 */
public class Tiger extends Predator {
    /**
     * Конструктор особи тигра
     * @param Name Имя
     * @param Number идентификационный номер
     * @param Food объем потребляемой пищи (целое число кг)
     */
    public Tiger(String Name, int Number, int Food){
        this.setNumber(Number);
        this.setName(Name);
        this.setFood(Food);
    }

    @Override
    public String toString(){
        return String.format("Tiger %s: inventory number = %d, food consumption = %dkg", this.getName(), this.getNumber(), this.getFood());
    }
}
