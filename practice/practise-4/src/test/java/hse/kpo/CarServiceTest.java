package hse.kpo;

import hse.kpo.domains.Customer;
import hse.kpo.factories.HandCarFactory;
import hse.kpo.factories.LevitatingCarFactory;
import hse.kpo.factories.PedalCarFactory;
import hse.kpo.params.EmptyEngineParams;
import hse.kpo.params.PedalEngineParams;
import hse.kpo.services.CarService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
class CarServiceTest {

    @Autowired
    CarService carService;

    @Autowired
    PedalCarFactory pedalCarFactory;

    @Autowired
    HandCarFactory handCarFactory;

    @Autowired
    LevitatingCarFactory levitateCarFactory;

    @Test
    @DirtiesContext
    @DisplayName("Тест добавления педального автомобиля и выдачи клиенту")
    void carServicePedalCreateTest() {
        carService.addCar(pedalCarFactory, new PedalEngineParams(100));
        var customer = new Customer("Boba", 6, 3, 15);
        var car = carService.takeCar(customer);
        Assertions.assertNotNull(car);
        Assertions.assertEquals(1, car.getVIN());
    }

    @Test
    @DirtiesContext
    @DisplayName("Тест добавления ручного автомобиля и выдачи клиенту, тест на ошибку")
    void carServiceHandCreateTest() {
        carService.addCar(handCarFactory, EmptyEngineParams.DEFAULT);
        var customer = new Customer("Beba", 6, 0, 15);
        var car = carService.takeCar(customer);
        Assertions.assertEquals(car, null);
    }

    @Test
    @DirtiesContext
    @DisplayName("Тест добавления левитирующего автомобиля и выдачи клиенту")
    void carServiceHandLevitTest() {
        carService.addCar(levitateCarFactory, EmptyEngineParams.DEFAULT);
        var customer = new Customer("Biba", 6, 0, 888);
        var car = carService.takeCar(customer);
        Assertions.assertNotNull(car);
        Assertions.assertEquals(1, car.getVIN());
    }
}