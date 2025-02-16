package hse.zoo.zoo.commands;

import hse.zoo.zoo.interfaces.ICommand;
import hse.zoo.zoo.interfaces.IAnimalService;
import hse.zoo.zoo.property.domains.herbos.Monkey;
import hse.zoo.zoo.property.domains.herbos.Rabbit;
import hse.zoo.zoo.property.domains.predators.Tiger;
import hse.zoo.zoo.property.domains.predators.Wolf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AddAnimalCommand implements ICommand {
    private final Scanner scanner;
    private final IAnimalService animalService;

    @Autowired
    public AddAnimalCommand(IAnimalService animalService, Scanner scanner){
        this.scanner = scanner;
        this.animalService = animalService;
    }

    @Override
    public void execute(){
        System.out.print("Kind: ");
        var kind = scanner.nextLine().trim();

        System.out.print("Name: ");
        var name = scanner.nextLine().trim();

        System.out.print("Inventory number: ");
        var number = scanner.nextLine().trim();

        System.out.print("Food consumption: ");
        var food = scanner.nextLine().trim();

        switch (kind) {
            case "Monkey" -> {
                System.out.print("Kindness: ");
                var kindness = scanner.nextLine().trim();
                animalService.add(new Monkey(name, Integer.parseInt(number), Integer.parseInt(food), Integer.parseInt(kindness)));
            }
            case "Rabbit" -> {
                System.out.print("Kindness: ");
                var kindness = scanner.nextLine().trim();
                animalService.add(new Rabbit(name, Integer.parseInt(number), Integer.parseInt(food), Integer.parseInt(kindness)));
            }
            case "Wolf" -> animalService.add(new Wolf(name, Integer.parseInt(number), Integer.parseInt(food)));
            case "Tiger" -> animalService.add(new Tiger(name, Integer.parseInt(number), Integer.parseInt(food)));
            default -> System.out.println("Wrong Input! Try again");
        }
    }

    @Override
    public String getName(){
        return "addAnimal";
    }

    @Override
    public String getDescription(){
        return "add an animal to zoo. Possible kinds: Tiger, Wolf, Monkey, Rabbit";
    }
}
