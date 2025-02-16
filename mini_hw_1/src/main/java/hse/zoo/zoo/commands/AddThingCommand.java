package hse.zoo.zoo.commands;

import hse.zoo.zoo.interfaces.ICommand;
import hse.zoo.zoo.interfaces.IThingService;
import hse.zoo.zoo.property.domains.things.Computer;
import hse.zoo.zoo.property.domains.things.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Scanner;

@Component
public class AddThingCommand implements ICommand {
    private final Scanner scanner;
    private final IThingService thingService;

    @Autowired
    public AddThingCommand(IThingService thingService, Scanner scanner){
        this.thingService = thingService;
        this.scanner = scanner;
    }

    @Override
    public void execute(){
        System.out.print("Kind: ");
        var kind = scanner.nextLine().trim();

        System.out.print("Name: ");
        var name = scanner.nextLine().trim();

        System.out.print("Inventory number: ");
        var number = scanner.nextLine().trim();

        if (Objects.equals(kind, "Computer")){
            thingService.add(new Computer(name, Integer.parseInt(number)));
        } else if (Objects.equals(kind, "Table")) {
            thingService.add(new Table(name, Integer.parseInt(number)));
        } else{
            System.out.println("Wrong Input! Try again");
        }
    }

    @Override
    public String getName(){
        return "addThing";
    }

    @Override
    public String getDescription(){
        return "add a thing to zoo. Possible kinds: Computer, Table";
    }
}
