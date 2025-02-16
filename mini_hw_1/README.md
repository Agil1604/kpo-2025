# Краткая идея решения
Приложение запускается через [main](./src/main/java/hse/zoo/Main.java)

[Приложение](./src/main/java/hse/zoo/application) отвечает только за прогон команд, которые находятся в папке [./zoo/commands](./src/main/java/hse/zoo/zoo/commands).

[Команды](./src/main/java/hse/zoo/zoo/commands) используют готовые реализации команд, которые находятся в [сервисах](./src/main/java/hse/zoo/zoo/services).

Классы животных реализуются в папке property.

# DI container
Применен с помощью ioc контейнера в Spring для команд и сервисов

# Соблюдение принципов SOLID
## S - Single Responsibility Principle
Все классы соблюдают принцип единой ответственности, так как каждый класс отвечает только за одну задачу.

Например, класс [App](./src/main/java/hse/zoo/application/App.java) отвечает только за запуск консольного приложения.
Класс [AnimalService](./src/main/java/hse/zoo/zoo/services/AnimalService.java) отвечает только за учет животных.
Класс [ThingService](./src/main/java/hse/zoo/zoo/services/ThingService.java) отвечает только за учет предметов.

## O - Open closed Principle
Классы доступны для расширения без изменения внутренностей класса. Например, [CommandHandler](./src/main/java/hse/zoo/zoo/commands/CommandHandler.java) открыт для регистрации новых команд и не надо ничего менять внутри.

## L - Liskov substitution Principle
Вместо всех базовых классов без проблем можно подставить дочерние. Это используется в сервисах (например, [AnimalService](./src/main/java/hse/zoo/zoo/services/AnimalService.java)): вместо [Animal](./src/main/java/hse/zoo/zoo/property/abstract_classes/Animals/Animal.java) можно спокойно подставить любого наследника данного класса (Tiger, Wolf, Monkey, Rabbit).

## I - Interface Segregation Principle
В данном коде есть следующие интерфейсы:
- [IAlive](./src/main/java/hse/zoo/zoo/property/interfaces/IAlive.java)
- [IInventory](./src/main/java/hse/zoo/zoo/property/interfaces/IInventory.java)
- [INamed](./src/main/java/hse/zoo/zoo/property/interfaces/INamed.java)
- [IAnimalService](./src/main/java/hse/zoo/zoo/interfaces/IAnimalService.java)
- [ICommand](./src/main/java/hse/zoo/zoo/interfaces/IAnimalService.java)
- [IThingService](./src/main/java/hse/zoo/zoo/interfaces/IThingService.java)
- [IVetClinic](./src/main/java/hse/zoo/zoo/interfaces/IVetClinic.java)
- [IWorkerService](./src/main/java/hse/zoo/zoo/interfaces/IWorkerService.java)

И следующие абстрактные классы:
- [Animal](./src/main/java/hse/zoo/zoo/property/abstract_classes/Animals/Animal.java)
- [Herbo](./src/main/java/hse/zoo/zoo/property/abstract_classes/Animals/Herbo.java)
- [Predator](./src/main/java/hse/zoo/zoo/property/abstract_classes/Animals/Predator.java)
- [Thing](./src/main/java/hse/zoo/zoo/property/abstract_classes/Thing.java)
- [Worker](./src/main/java/hse/zoo/zoo/property/abstract_classes/Worker.java)

В этих интерфейсах/абстрактных классах все заданные методы реализованы и используются наследниками. 
В них отсутствует лишняя информация. 
То есть данный принцип выполняется.

## D - Dependency Inversion Principle

Все связи между классами обеспечиваются либо интерфейсами, либо абстрактными классами. То есть нет зависимостей между конкретными реализациями.