package hse.kpo;

import hse.kpo.factories.HandCarFactory;
import hse.kpo.params.EmptyEngineParams;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HandCarFactoryTest {

    @Autowired
    HandCarFactory handCarFactory;

    @Test
    @DisplayName("тест создания ручного автомобиля")
    void handCarFactoryTest() {
        var handCar = handCarFactory.createCar(EmptyEngineParams.DEFAULT, 15);
        Assertions.assertNotNull(handCar);
        Assertions.assertEquals(handCar.getVIN(), 15);
    }
}