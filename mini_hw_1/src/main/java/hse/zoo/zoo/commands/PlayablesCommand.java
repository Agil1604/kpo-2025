package hse.zoo.zoo.commands;

import hse.zoo.zoo.interfaces.ICommand;
import hse.zoo.zoo.interfaces.IAnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlayablesCommand implements ICommand {
    private final IAnimalService animalService;

    @Autowired
    public PlayablesCommand(IAnimalService animalService){
        this.animalService = animalService;
    }

    @Override
    public void execute(){
        System.out.println("Playable animals:");
        animalService.getPlayables();
    }

    @Override
    public String getName(){
        return "playables";
    }

    @Override
    public String getDescription(){
        return "prints the list of animals that can be used in contact zoo";
    }

}
