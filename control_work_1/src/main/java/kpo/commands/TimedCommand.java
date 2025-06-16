package kpo.commands;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TimedCommand implements Command {
    private final Command command;

    @Override
    public void execute() {
        long startTime = System.currentTimeMillis();
        command.execute();
        long duration = System.currentTimeMillis() - startTime;
        System.out.println("Время выполнения: " + duration + " мс");
    }
}