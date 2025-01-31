package studying.domains;

import lombok.Getter;
import lombok.ToString;
import studying.interfaces.IEngine;

/**
 * Класс для машин
 */
@ToString
public class Car {

    private IEngine engine;

    @Getter
    private int VIN;

    public Car(int VIN, IEngine engine) {
        this.VIN = VIN;
        this.engine = engine;
    }

    /**
     * Внутри метода просто вызываем соответствующий метод двигателя и проверяет совместимость машины и клиента
     * @param customer клиент
     * @return может ли клиент купить данную машину
     */
    public boolean isCompatible(Customer customer) {
        return this.engine.isCompatible(customer);
    }
}
