package studying.domains;

import lombok.ToString;
import studying.interfaces.IEngine;

/**
 * Ручной двигатель
 */
@ToString
public class HandEngine implements IEngine {
    @Override
    public boolean isCompatible(Customer customer) {
        return customer.getHandPower() > 5;
    }
}
