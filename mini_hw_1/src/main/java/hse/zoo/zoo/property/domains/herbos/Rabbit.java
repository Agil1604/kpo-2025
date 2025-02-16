package hse.zoo.zoo.property.domains.herbos;

import hse.zoo.zoo.property.abstract_classes.Animals.Herbo;

/**
 * Класс для кроликов
 */
public class Rabbit extends Herbo {
    /**
     * Конструктор особи кролика
     * @param Name Имя
     * @param Number идентификационный номер
     * @param Food объем потребляемой пищи (целое число кг)
     * @param Kindness уровень доброты (целое число от 1 до 10)
     */
    public Rabbit(String Name, int Number, int Food, int Kindness){
        if (Kindness > 10){Kindness = 10;}
        else if (Kindness < 0) {Kindness = 0;}

        this.setName(Name);
        this.setNumber(Number);
        this.setKindness(Kindness);
        this.setFood(Food);
    }
    @Override
    public String toString(){
        return String.format("Rabbit %s: inventory number = %d, food consumption = %dkg and Kindness = %d", this.getName(), this.getNumber(), this.getFood(), this.getKindness());
    }
}
