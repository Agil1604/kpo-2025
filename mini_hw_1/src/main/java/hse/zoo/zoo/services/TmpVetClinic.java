package hse.zoo.zoo.services;

import hse.zoo.zoo.interfaces.IVetClinic;
import hse.zoo.zoo.property.abstract_classes.Animals.Animal;
import org.springframework.stereotype.Component;

/**
 * Временное решение для клиник
 */
@Component
public class TmpVetClinic implements IVetClinic {

    /**
     * С вероятностью 0,9 говорит, что особь здоровая
     * @param animal проверяемая особь
     * @return true, если животное здоровое, false, иначе
     */
    @Override
    public boolean is_healthy(Animal animal) {
        return ((int) (Math.random()*10)) != 0;
    }
}
