package hse.kpo;

import hse.kpo.domains.Customer;
import hse.kpo.factories.HandCarFactory;
import hse.kpo.factories.LevitatingCarFactory;
import hse.kpo.factories.PedalCarFactory;
import hse.kpo.params.EmptyEngineParams;
import hse.kpo.params.PedalEngineParams;
import hse.kpo.services.CarService;
import hse.kpo.services.CustomerStorage;
import hse.kpo.services.HseCarService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.atomic.AtomicInteger;

@SpringBootTest
class HseCarServiceTest {
    @Autowired
    CarService carService;

    @Autowired
    CustomerStorage customerStorage;

    @Autowired
    HseCarService hseCarService;

    @Autowired
    HandCarFactory handCarFactory;

    @Autowired
    LevitatingCarFactory levitateCarFactory;

    @Autowired
    PedalCarFactory pedalCarFactory;

    @Test
    @DisplayName("тест корректности продаж")
    void hseCarServiceBigCoolTest() {
        carService.addCar(pedalCarFactory, new PedalEngineParams(1));
        carService.addCar(levitateCarFactory, EmptyEngineParams.DEFAULT);
        carService.addCar(handCarFactory, EmptyEngineParams.DEFAULT);

        customerStorage.addCustomer(new Customer("Pupu", 20, 1, 100));
        customerStorage.addCustomer(new Customer("Pepe", 0, 5, 100));
        customerStorage.addCustomer(new Customer("Pipi", 2, 0, 555));

        hseCarService.sellCars();
        var customers = customerStorage.getCustomers();
        AtomicInteger soldCars = new AtomicInteger();

        customers.forEach(customer -> {
            if (customer.getCar() != null) soldCars.getAndIncrement();
        });
        Assertions.assertEquals(soldCars.get(), 2);
    }
}