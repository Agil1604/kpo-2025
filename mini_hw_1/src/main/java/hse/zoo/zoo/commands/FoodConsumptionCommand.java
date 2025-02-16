package hse.zoo.zoo.commands;

import hse.zoo.zoo.interfaces.ICommand;
import hse.zoo.zoo.interfaces.IAnimalService;
import hse.zoo.zoo.interfaces.IWorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FoodConsumptionCommand implements ICommand {
    private final IAnimalService animalService;
    private final IWorkerService workerService;

    @Autowired
    public FoodConsumptionCommand(IAnimalService animalService, IWorkerService workerService){
        this.animalService = animalService;
        this.workerService = workerService;
    }

    @Override
    public void execute(){
        int animalFood = animalService.totalFood();
        int workerFood = workerService.totalFood();
        System.out.println("total daily consumption: " + (animalFood + workerFood) + "kg");
        System.out.println("animal consumption: " + animalFood + "kg");
        System.out.println("worker consumption: " + workerFood + "kg");
    }

    @Override
    public String getName(){
        return "foodConsumption";
    }

    @Override
    public String getDescription(){
        return "calculates total daily food consumption by animals and workers";
    }
}
