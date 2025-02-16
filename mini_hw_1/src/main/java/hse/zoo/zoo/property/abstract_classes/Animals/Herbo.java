package hse.zoo.zoo.property.abstract_classes.Animals;

import lombok.Getter;
import lombok.Setter;

/**
 * Класс травоядных животных
 */
@Setter
@Getter
public class Herbo extends Animal{
    private int Kindness;

    @Override
    public boolean isPlayable(){
        return this.Kindness >= 5;
    }
}
