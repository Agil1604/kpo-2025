package hse.zoo.application;

import hse.zoo.zoo.commands.CommandHandler;

public class App{
    private final CommandHandler commandHandler;

    public App(CommandHandler commandHandler){
        this.commandHandler = commandHandler;
    }

    public void run(){
        boolean isRunning = true;
        System.out.println("App is running. Type 'help' for getting list of commands");
        while(isRunning){
            isRunning = commandHandler.handleCommand();
        }
    }

}
