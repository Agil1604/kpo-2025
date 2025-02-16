import hse.zoo.zoo.property.domains.herbos.Monkey;
import hse.zoo.zoo.property.domains.herbos.Rabbit;
import hse.zoo.zoo.property.domains.predators.Tiger;
import hse.zoo.zoo.property.domains.predators.Wolf;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AnimalCreateTest {
    @Test
    @DisplayName("Checks ability to build monkey")
    public void createMonkey(){
        var monkey = new Monkey("Roma", 1, 3, 9);
        Assertions.assertNotNull(monkey);
    }

    @Test
    @DisplayName("Checks ability to build monkey")
    public void createRabbit(){
        var monkey = new Rabbit("Roma", 1, 3, 9);
        Assertions.assertNotNull(monkey);
    }

    @Test
    @DisplayName("Checks ability to build monkey")
    public void createWolf(){
        var monkey = new Wolf("Roma", 1, 3);
        Assertions.assertNotNull(monkey);
    }

    @Test
    @DisplayName("Checks ability to build monkey")
    public void createTiger(){
        var monkey = new Tiger("Roma", 1, 3);
        Assertions.assertNotNull(monkey);
    }
}
