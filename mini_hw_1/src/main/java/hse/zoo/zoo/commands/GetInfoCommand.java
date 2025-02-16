package hse.zoo.zoo.commands;

import hse.zoo.zoo.interfaces.ICommand;
import hse.zoo.zoo.interfaces.IAnimalService;
import hse.zoo.zoo.interfaces.IThingService;
import hse.zoo.zoo.interfaces.IWorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetInfoCommand implements ICommand {
    private final IAnimalService animalService;
    private final IThingService thingService;
    private final IWorkerService workerService;

    @Autowired
    public GetInfoCommand(IAnimalService animalService, IThingService thingService, IWorkerService workerService){
        this.animalService = animalService;
        this.thingService = thingService;
        this.workerService = workerService;
    }
    @Override
    public void execute(){
        System.out.println("Total info about zoo: ");
        System.out.println("Animals: ");
        animalService.printInfo();
        System.out.println("Things: ");
        thingService.printInfo();
        System.out.println("Workers: ");
        workerService.printInfo();
    }

    @Override
    public String getName(){
        return "getInfo";
    }

    @Override
    public String getDescription(){
        return "print info about everything in the zoo";
    }

}
