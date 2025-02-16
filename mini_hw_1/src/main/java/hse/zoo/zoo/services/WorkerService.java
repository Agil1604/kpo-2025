package hse.zoo.zoo.services;

import hse.zoo.zoo.interfaces.IWorkerService;
import hse.zoo.zoo.property.abstract_classes.Worker;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для учета сотрудников
 */
@Component
public class WorkerService implements IWorkerService {
    private final List<Worker> workers = new ArrayList<>();

    @Override
    public int getCount() {
        return workers.size();
    }

    @Override
    public void printInfo() {
        workers.forEach(worker -> System.out.println(worker.getName()));
    }

    @Override
    public void add(Worker worker) {
        this.workers.add(worker);
    }

    @Override
    public int totalFood(){
        return workers.stream().mapToInt(Worker::getFood).sum();
    }
}
