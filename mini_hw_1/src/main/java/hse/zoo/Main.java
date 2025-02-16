package hse.zoo;

import hse.zoo.application.AppConfig;
import hse.zoo.zoo.commands.*;
import hse.zoo.application.App;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;



public class Main {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        System.out.println("Hello!");

        CommandHandler commandHandler = context.getBean(CommandHandler.class);

        var app = new App(commandHandler);
        app.run();
    }
}





