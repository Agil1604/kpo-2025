package hse.zoo.zoo.property.abstract_classes;

import hse.zoo.zoo.property.interfaces.IAlive;
import hse.zoo.zoo.property.interfaces.INamed;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Базовый класс для работников зоопарка
 */
@ToString
@Getter
@Setter
public abstract class Worker implements IAlive, INamed {
    private int Food;
    private String Name;
}
