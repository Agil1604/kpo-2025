package hse.zoo.zoo.commands;

import hse.zoo.zoo.interfaces.ICommand;
import hse.zoo.zoo.interfaces.IAnimalService;
import hse.zoo.zoo.interfaces.IThingService;
import hse.zoo.zoo.interfaces.IWorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReportCountCommand implements ICommand {
    private final IAnimalService animalService;
    private final IThingService thingService;
    private final IWorkerService workerService;

    @Autowired
    public ReportCountCommand(IAnimalService animalService, IThingService thingService, IWorkerService workerService){
        this.animalService = animalService;
        this.thingService = thingService;
        this.workerService = workerService;
    }

    @Override
    public void execute(){
        int animalCount = animalService.getCount();
        int workerCount = workerService.getCount();
        int thingCount = thingService.getCount();
        System.out.println("total count: " + (animalCount + workerCount + thingCount));
        System.out.println("animal count: " + animalCount);
        System.out.println("thing count: " + thingCount);
        System.out.println("worker count: " + workerCount);
    }

    @Override
    public String getName(){
        return "reportCount";
    }

    @Override
    public String getDescription(){
        return "shows numbers of everything in the zoo";
    }
}
