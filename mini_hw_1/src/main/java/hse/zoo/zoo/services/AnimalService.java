package hse.zoo.zoo.services;

import hse.zoo.zoo.interfaces.IAnimalService;
import hse.zoo.zoo.interfaces.IVetClinic;
import hse.zoo.zoo.property.abstract_classes.Animals.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс учета животных
 */
@Component
public class AnimalService implements IAnimalService {
    private final List<Animal> animals = new ArrayList<>();
    private final IVetClinic clinic;

    /**
     * Конструктор сервиса управления животными
     * @param clinic Клиника проверяющая здоровье животных
     */
    @Autowired
    public AnimalService(IVetClinic clinic){
        this.clinic = clinic;
    }

    @Override
    public int getCount() {
        return animals.size();
    }

    @Override
    public void printInfo() {
        animals.forEach(animal -> System.out.println(animal.getName() + " with inventory number " + animal.getNumber()));
    }

    @Override
    public void add(Animal animal) {
        if(clinic.is_healthy(animal)){
            this.animals.add(animal);
            System.out.println("Animal " + animal.getName() + " was added to zoo");
        } else{
            System.out.println("Animal " + animal.getName() + " is unhealthy");
        }
    }

    @Override
    public int totalFood(){
        return animals.stream().mapToInt(Animal::getFood).sum();
    }

    @Override
    public void getPlayables() {
        animals.stream().filter(Animal::isPlayable).toList().forEach(System.out::println);
    }
}
