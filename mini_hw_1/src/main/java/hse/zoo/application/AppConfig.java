package hse.zoo.application;

import hse.zoo.zoo.commands.AddAnimalCommand;
import hse.zoo.zoo.services.AnimalService;
import hse.zoo.zoo.services.ThingService;
import hse.zoo.zoo.services.TmpVetClinic;
import hse.zoo.zoo.services.WorkerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
@ComponentScan(basePackages = "hse.zoo.zoo.commands")
public class AppConfig {
    @Bean
    public Scanner scanner(){
        return new Scanner(System.in);
    }

    @Bean
    public AnimalService animalService(){
        return new AnimalService(new TmpVetClinic());
    }

    @Bean
    public WorkerService workerService(){
        return new WorkerService();
    }

    @Bean
    public ThingService thingService(){
        return new ThingService();
    }
//    @Bean
//    public AddAnimalCommand
//            commandHandler.registerCommand("addAnimal",new AddAnimalCommand(animalService, scanner));

}
