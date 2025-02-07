# Список терминов семинара

## ServiceLocator
- Определение: Паттерн, предоставляющий глобальную точку доступа к сервисам, вместо их явного создания.
- Пример кода:
```Java
public class ServiceLocator {
    private static Map<String, Object> services = new HashMap<>();
    
    public static void registerService(String name, Object service) {
        services.put(name, service);
    }

    public static Object getService(String name) { 
        return services.get(name);
    }
}

// Использование
ServiceLocator.registerService("paymentService", new PaymentService());
PaymentService service = (PaymentService) ServiceLocator.getService("paymentService");
```

## DIP (Dependency Inversion Principle)
- Определение: Принцип SOLID, согласно которому:
  - Высокоуровневые модули не должны зависеть от низкоуровневых. Оба зависят от абстракций.
  - Абстракции не должны зависеть от деталей. Детали зависят от абстракций.
- Пример кода:
```Java
// Абстракция
interface Storage {
    void save(String data);
}
  
// Низкоуровневый модуль
class Database implements Storage {
    public void save(String data) { /* ... */ }
}

// Высокоуровневый модуль
class UserService {
    private Storage storage; // Зависимость от абстракции

    public UserService(Storage storage) {
        this.storage = storage;
    }
}
```
## IoC (Inversion of Control)
- Определение: Концепция передачи управления созданием и связыванием объектов контейнеру (фреймворку).
- Пример в Spring: Внедрение зависимостей через конструктор или аннотации (`@Autowired`), а не создание объектов напрямую.
## Singleton
- Определение: Паттерн, гарантирующий, что класс имеет только один экземпляр.
- Пример кода:
```Java
public class DatabaseConnection {
    private static DatabaseConnection instance;

    private DatabaseConnection() { /* ... */ }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
}
```
## Prototype
- Определение: Паттерн, создающий новые объекты путем клонирования существующего (прототипа).
- Пример кода:
```Java
class Sheep implements Cloneable {
private String name;

    public Sheep(String name) { this.name = name; }

    @Override
    public Sheep clone() throws CloneNotSupportedException {
        return (Sheep) super.clone();
    }
}

// Использование
Sheep original = new Sheep("Dolly");
Sheep clone = original.clone();
```

## Юнит тесты junit
- Определение: Тестирование отдельных компонентов (методов, классов) на корректность.
- Пример кода:
```Java
class CalculatorTest {
    @Test
    void testAdd() {
        Calculator calc = new Calculator();
        assertEquals(5, calc.add(2, 3));
    }
}
```
## Mockito (mock + spy)
- Mock: Объект-заглушка, имитирующий поведение реального объекта.
- Spy: Обёртка над реальным объектом, позволяющая отслеживать вызовы методов.
- Пример теста:
```Java
@Test
void testUserService() {
UserRepository mockRepo = Mockito.mock(UserRepository.class);
UserService service = new UserService(mockRepo);

    Mockito.when(mockRepo.findById(1L)).thenReturn(new User("Alice"));

    User user = service.getUser(1L);
    assertEquals("Alice", user.getName());
}
```
## @SpringBootTest
- Определение: Аннотация для запуска интеграционных тестов с полной загрузкой контекста Spring.
- Пример:
```Java
@SpringBootTest
class OrderServiceIntegrationTest {
@Autowired
private OrderService orderService;

    @Test
    void testCreateOrder() {
        Order order = orderService.createOrder();
        assertNotNull(order);
    }
}
```

## @Autowired
- Определение: Аннотация Spring для автоматического внедрения зависимостей.
- Пример:
```Java
@Service
public class UserService {
    @Autowired
    private UserRepository repository; // Spring найдёт и внедрит реализацию
}
```
## @Component
- Определение: Аннотация, помечающая класс как компонент Spring (кандидат для автоматического создания бина).
- Пример:
```Java
@Component
public class LoggerService {
    public void log(String message) { /* ... */ }
}
```