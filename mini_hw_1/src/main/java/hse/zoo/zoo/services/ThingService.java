package hse.zoo.zoo.services;

import hse.zoo.zoo.interfaces.IThingService;
import hse.zoo.zoo.property.abstract_classes.Thing;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс учета предметов
 */
@Component
public class ThingService implements IThingService {
    private final List<Thing> things = new ArrayList<>();

    @Override
    public int getCount() {
        return things.size();
    }

    @Override
    public void printInfo() {
        things.forEach(thing -> System.out.println(thing.getName() + " with inventory number " + thing.getNumber()));
    }

    @Override
    public void add(Thing thing) {
        this.things.add(thing);
    }

}
