package hse.zoo.zoo.property.abstract_classes.Animals;

import hse.zoo.zoo.property.interfaces.IAlive;
import hse.zoo.zoo.property.interfaces.IInventory;
import hse.zoo.zoo.property.interfaces.INamed;
import lombok.Getter;
import lombok.Setter;

/**
 * Класс для общего вида животных
 */
@Getter
@Setter
public abstract class Animal implements IAlive, IInventory, INamed {
    private int Food;
    private int Number;
    private String Name;


    /**
     * Проверяет достаточно ли добрая данная особь, чтобы попасть в зоопарк
     * @return true, если можно, false, иначе
     */
    public abstract boolean isPlayable();

}
