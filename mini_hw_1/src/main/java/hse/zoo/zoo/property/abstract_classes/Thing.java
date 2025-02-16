package hse.zoo.zoo.property.abstract_classes;

import hse.zoo.zoo.property.interfaces.IInventory;
import hse.zoo.zoo.property.interfaces.INamed;
import lombok.Getter;
import lombok.Setter;

/**
 * Базовый класс для предметов зоопарка
 */
@Getter
@Setter
public abstract class Thing implements IInventory, INamed {
    private int Number;
    private String Name;
}
