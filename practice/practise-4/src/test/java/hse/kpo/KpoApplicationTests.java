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
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KpoApplicationTests {

	@Autowired
	private CarService carService;

	@Autowired
	private CustomerStorage customerStorage;

	@Autowired
	private HseCarService hseCarService;

	@Autowired
	private PedalCarFactory pedalCarFactory;

	@Autowired
	private HandCarFactory handCarFactory;

	@Autowired
	private LevitatingCarFactory levitatingCarFactory;

	@Test
	@DisplayName("Тест загрузки контекста")
	void contextLoads() {
		Assertions.assertNotNull(carService);
		Assertions.assertNotNull(customerStorage);
		Assertions.assertNotNull(hseCarService);
	}

	@Test
	@DisplayName("Тест загрузки контекста")
	void hseCarServiceTest() {
		customerStorage.addCustomer(new Customer("Ivan",6,4, 500));
		customerStorage.addCustomer(new Customer("Nikita",4,4, 125));
		customerStorage.addCustomer(new Customer("Maksim",4,6, 163));
		customerStorage.addCustomer(new Customer("Petya",6,6, 25));

		carService.addCar(levitatingCarFactory, EmptyEngineParams.DEFAULT);

		carService.addCar(pedalCarFactory, new PedalEngineParams(6));
		carService.addCar(pedalCarFactory, new PedalEngineParams(6));

		carService.addCar(handCarFactory, EmptyEngineParams.DEFAULT);
		carService.addCar(handCarFactory, EmptyEngineParams.DEFAULT);

		customerStorage.getCustomers().stream().map(Customer::toString).forEach(System.out::println);

		hseCarService.sellCars();

		customerStorage.getCustomers().stream().map(Customer::toString).forEach(System.out::println);
	}

	@Test
	@DisplayName("Тест добавления пользователя")
	void CarServiceTest(){

	}
}
