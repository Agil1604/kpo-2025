package hse.kpo;

import hse.kpo.domains.Customer;
import hse.kpo.factories.PedalCarFactory;
import hse.kpo.params.PedalEngineParams;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PedalCarFactoryTest {

    @Autowired
    PedalCarFactory pedalCarFactory;

    @Test
    @DisplayName("тест создания педального автомобиля")
    void pedalCarFactoryTest() {
        var carParams = new PedalEngineParams(12);
        var car = pedalCarFactory.createCar(carParams, 15);
        Assertions.assertNotNull(car);
        Assertions.assertEquals(car.getVIN(), 15);
    }

    @Test
    @DisplayName("тест пододимости покупателя двигателю, тест на ошибку")
    void pedalCarFactoryCompatibleTest() {
        var carParams = new PedalEngineParams(12);
        var car = pedalCarFactory.createCar(carParams, 15);
        var customer = new Customer("Buba", 1, 4, 108);

        Assertions.assertEquals(false, car.isCompatible(customer));
    }
}