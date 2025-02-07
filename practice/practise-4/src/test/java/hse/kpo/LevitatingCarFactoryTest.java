package hse.kpo;

import hse.kpo.domains.Customer;
import hse.kpo.factories.LevitatingCarFactory;
import hse.kpo.params.EmptyEngineParams;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LevitatingCarFactoryTest {

    @Autowired
    LevitatingCarFactory levitatingCarFactory;


    @Test
    @DisplayName("тест создания левитирующего автомобиля")
    void levitatingCarFactoryTest() {
        var car = levitatingCarFactory.createCar(EmptyEngineParams.DEFAULT, 15);
        Assertions.assertNotNull(car);
        Assertions.assertEquals(car.getVIN(), 15);
    }

    @Test
    @DisplayName("тест подохимости покупателя двигателю")
    void levitatingCarFactoryCompatibleTest() {
        var car = levitatingCarFactory.createCar(EmptyEngineParams.DEFAULT, 15);
        var customer = new Customer("Biba", 1, 4, 999);

        Assertions.assertEquals(true, car.isCompatible(customer));
    }
}